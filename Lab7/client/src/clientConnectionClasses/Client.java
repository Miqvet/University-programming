package clientConnectionClasses;


import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

import clientSystemClasses.UserManager;
import exceptions.ConnectionException;
import exceptions.NotInDeclaredLimitsException;
import transmitted.Request;
import transmitted.Response;
import transmitted.ServerStatus;
import transmitted.User;
import userHandler.AuthAsker;
import userHandler.AuthHandler;
import utilities.Output;

/**
 * Runs the client.
 */
public class Client {
    private User user;
    private final int reconnectionTimeout;
    private int reconnectionAttempts;
    private final int maxReconnectionAttempts;
    private String host;
    private int port;
    private static final String COMMAND_EXIT = "exit";
    private SocketChannel socketChannel;
    private ObjectOutputStream serverWriter;
    private ObjectInputStream serverReader;
    private final UserManager userManager;
    private final AuthHandler authHandler;
    public Client(String host, int port, UserManager userManager, int reconnectionTimeout, int maxReconnectionAttempts,AuthHandler authHandler){
        setHost(host);
        setPort(port);
        this.reconnectionAttempts = 0;
        this.userManager = userManager;
        this.reconnectionTimeout = reconnectionTimeout;
        this.maxReconnectionAttempts = maxReconnectionAttempts;
        this.authHandler = authHandler;
    }
    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void ClientRun(){
        try {
            boolean processingStatus = true;
            while (processingStatus) {
                try {
                    connectToServer();
                    processAuthorisation();
                    processingStatus = processRequestToServer();
                } catch (ConnectionException e) {
                    if (reconnectionAttempts >= maxReconnectionAttempts) {
                        Output.printerror("Превышено количество попыток подключения!" +
                                "\nПрограмма немедленно прекращает свою работу");
                        break;
                    }
                    try {
                        Thread.sleep(reconnectionTimeout);
                    } catch (IllegalArgumentException timeoutE) {
                        Output.printerror("Время ожидания подключения " + reconnectionTimeout +
                                " находится за пределами возможных значений!");
                        Output.println("Повторное подключение будет произведено немедленно.");
                    } catch (InterruptedException ex) {
                        Output.printerror("Во время ожидания наш поток был прерван другим потоком");
                    }
                }
                reconnectionAttempts++;
            }
            if (socketChannel != null) socketChannel.close();
            Output.println("Работа клиента успешно завершена.");
        } catch (IOException exception) {
            Output.printerror("Произошла ошибка при попытке закрыть соединение с сервером!\n" +
                    "проблема с сокетом и его закрытием");
        }
    }
    private void connectToServer() throws ConnectionException{
        try {
            socketChannel = SocketChannel.open(new InetSocketAddress(host, port));
            Output.println("Соединение с сервером успешно установлено.");
            Output.println("Ожидание разрешения на обмен данными...");
            serverWriter = new ObjectOutputStream(socketChannel.socket().getOutputStream());
            serverReader = new ObjectInputStream(socketChannel.socket().getInputStream());
            Output.println("Разрешение на обмен данными получено.");
        }catch (IOException exception) {
            throw new ConnectionException("Произошла ошибка при соединении с сервером!");
        }
    }
    private boolean processRequestToServer() {
        Request requestToServer = null;
        Response serverResponse = null;
        do {
            try {
                requestToServer = serverResponse != null ? userManager.createRequest(serverResponse.getStatus()) :
                        userManager.createRequest(null);
                if (requestToServer.isEmpty()) continue;
                serverWriter.writeObject(requestToServer);
                serverResponse = (Response) serverReader.readObject();
                Output.print(serverResponse.getResponse());
            } catch (ClassNotFoundException exception) {
                Output.printerror("Произошла ошибка при чтении полученных данных!");
            } catch (IOException exception) {
                Output.printerror("Соединение с сервером разорвано!");
                try {
                    reconnectionAttempts++;
                    connectToServer();
                } catch (ConnectionException e) {
                    Output.printerror("При попытке переподключиться к серверу произошли непредвиденные ошибки.");
                }
            }
        } while (!COMMAND_EXIT.equals(requestToServer.getCommand()));
        return false;
    }
    private void processAuthorisation() {
        Request requestToServer = null;
        Response serverResponse = null;
        do {
            try {
                requestToServer = authHandler.initializedUser();
                if (requestToServer.isEmpty()) continue;
                serverWriter.writeObject(requestToServer);
                serverResponse = (Response) serverReader.readObject();
                Output.print(serverResponse.getResponse());
            } catch (InvalidClassException | NotSerializableException exception) {
                Output.printerror("Произошла ошибка при отправке данных на сервер!");
            } catch (ClassNotFoundException exception) {
                Output.printerror("Произошла ошибка при чтении полученных данных!");
            } catch (IOException exception) {
                Output.printerror("Соединение с сервером разорвано!");
                try {
                    connectToServer();
                } catch (ConnectionException reconnectionException) {
                    Output.println("Попробуйте повторить авторизацию позднее.");
                }
            }
        } while (!serverResponse.getStatus().equals(ServerStatus.OK));
        user = requestToServer.getUser();
        userManager.setUser(user);
    }
}

