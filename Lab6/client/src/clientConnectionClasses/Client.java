package clientConnectionClasses;


import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

import clientSystemClasses.UserManager;
import exceptions.ConnectionErrorException;
import exceptions.NotInDeclaredLimitsException;
import transmitted.Request;
import transmitted.Response;
import utilities.Output;

/**
 * Runs the client.
 */
public class Client {
    private String host;
    private int port;
    private SocketChannel socketChannel;
    private ObjectOutputStream serverWriter;
    private ObjectInputStream serverReader;
    private UserManager userManager;
    public Client(String host, int port, UserManager userManager){
        setHost(host);
        setPort(port);
        this.userManager = userManager;
    }
    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        if (port <= 0) throw new RuntimeException("Port должен быть целым числом больше нуля");
        this.port = port;
    }

    public void ClientRun() throws IOException {
        try {
            boolean processingStatus = true;
            while (processingStatus) {
                try {
                    connectToServer();
                    processingStatus = processRequestToServer();
                } catch (ConnectionErrorException exception) {
                    System.out.println("Ошибка");
                }
            }
            if (socketChannel != null) socketChannel.close();
            Output.println("Работа клиента успешно завершена.");
        } catch (NotInDeclaredLimitsException e) {
            Output.printerror("Клиент не может быть запущен!");
        } catch (IOException exception) {
            Output.printerror("Произошла ошибка при попытке завершить соединение с сервером!");
        }
    }
    private void connectToServer() throws ConnectionErrorException, NotInDeclaredLimitsException {
        try {
            socketChannel = SocketChannel.open(new InetSocketAddress(host, port));
            Output.println("Соединение с сервером успешно установлено.");
            Output.println("Ожидание разрешения на обмен данными...");
            serverWriter = new ObjectOutputStream(socketChannel.socket().getOutputStream());
            serverReader = new ObjectInputStream(socketChannel.socket().getInputStream());
            Output.println("Разрешение на обмен данными получено.");
        } catch (IllegalArgumentException exception) {
            throw new NotInDeclaredLimitsException("Адрес сервера введен некорректно!");
        } catch (IOException exception) {
            throw new ConnectionErrorException("Произошла ошибка при соединении с сервером!");
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
            } catch (InvalidClassException | NotSerializableException exception) {
                Output.printerror("Произошла ошибка при отправке данных на сервер!");
            } catch (ClassNotFoundException exception) {
                Output.printerror("Произошла ошибка при чтении полученных данных!");
            } catch (IOException exception) {
                Output.printerror("Соединение с сервером разорвано!");
            }
        } while (!requestToServer.getCommand().equals("exit"));
        return false;
    }
}

