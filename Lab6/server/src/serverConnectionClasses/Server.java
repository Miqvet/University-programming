package serverConnectionClasses;

import exceptions.ClosingSocketException;
import exceptions.ConnectionErrorException;
import exceptions.OpeningServerSocketException;
import serverSystemClasses.CollectionManager;
import transmitted.Request;
import transmitted.Response;
import transmitted.ServerStatus;
import utilities.Output;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Server {
    private int port;
    public void setPort(int port) {
        if (port <= 0) throw new RuntimeException("Port должен быть целым числом больше нуля");
        this.port = 1234;
    }
    private int soTimeout;
    private CollectionManager collectionManager;
    private RequestHandler requestHandler = new RequestHandler();
    private ServerSocket serverSocket;
    public Server(int port,CollectionManager collectionManager){
        setPort(port);
        this.collectionManager = collectionManager;
    }

    public void serverRun(){
        try {
            openServerSocket();
            boolean processingStatus = true;
            while (processingStatus) {
                try (Socket clientSocket = connectToClient()) {
                    processingStatus = processClientRequest(clientSocket);
                } catch (ConnectionErrorException | SocketTimeoutException e) {
                    Output.printerror(e.getMessage());
                    break;
                } catch (IOException e) {
                    Output.printerror("Произошла ошибка при попытке завершить соединение с клиентом!");
                }
            }
            stop();
        } catch (OpeningServerSocketException e) {
            Output.printerror(e.getMessage());
            Output.printerror("Сервер не может быть запущен!");
        }
    }
    private void openServerSocket() throws OpeningServerSocketException {
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(soTimeout);
        } catch (IllegalArgumentException e) {
            throw new OpeningServerSocketException("Порт" + port +  " находится за пределами возможных значений!");
        } catch (IOException e) {
            throw new OpeningServerSocketException("Произошла ошибка при попытке использовать порт" + port + "'!");
        }
    }
    private Socket connectToClient() throws SocketTimeoutException, ConnectionErrorException{
        try {
            Socket clientSocket = serverSocket.accept();
            return clientSocket;
        } catch (SocketTimeoutException e) {
            throw new SocketTimeoutException("Превышено время ожидания подключения!");
        } catch (IOException e) {
            throw new ConnectionErrorException("Произошла ошибка при соединении с клиентом!");
        }
    }
    private boolean processClientRequest(Socket clientSocket) {
        Request userRequest = null;
        Response responseToUser = null;
        try (ObjectInputStream clientReader = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream clientWriter = new ObjectOutputStream(clientSocket.getOutputStream())) {
            // прочтение запроса
            do{
                System.out.println("Ожидание запроса");
                userRequest = (Request) clientReader.readObject();
                // обработка запроса
                System.out.println("Запрос получен");
                System.out.println(userRequest.getCommand());
                System.out.println(userRequest.getObjArgument());
                System.out.println("ожидание");
                responseToUser = RequestHandler.convertRequest(userRequest, collectionManager);
                // отправка
                System.out.println("Запрос обработан");
                System.out.println(responseToUser.getStatus());
                System.out.println(responseToUser.getResponse());
                System.out.println("ожидание");
                clientWriter.writeObject(responseToUser);
                clientWriter.flush();
                System.out.println("всё ок\n\n");
            } while(responseToUser.getStatus() != ServerStatus.SERVER_DISABLED);
            return false;
        } catch (ClassNotFoundException exception) {
            Output.printerror("Произошла ошибка при чтении полученных данных!");
        } catch (InvalidClassException | NotSerializableException exception) {
            Output.printerror("Произошла ошибка при отправке данных на клиент!");
        } catch (IOException exception) {
            if (userRequest == null) {
                Output.printerror("Непредвиденный разрыв соединения с клиентом!");
            } else {
                Output.println("Клиент успешно отключен от сервера!");
            }
        }
        return true;
    }
    private void stop() {
        try {
            if (serverSocket == null)
                throw new ClosingSocketException("Невозможно завершить работу еще не запущенного сервера!");
            serverSocket.close();
        } catch (ClosingSocketException e) {
            Output.printerror(e.getMessage());
        } catch (IOException exception) {
            Output.printerror("Произошла ошибка при завершении работы сервера!");
        }
    }
}
