package serverConnectionClasses;

import exceptions.ClosingSocketException;
import exceptions.OpeningServerSocketException;
import multiLine.*;
import serverSystemClasses.CollectionManager;
import transmitted.Request;
import transmitted.Response;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Logger;
import java.util.concurrent.locks.ReentrantLock;
public class Server {
    public static ReentrantLock locker = new ReentrantLock();
    ForkJoinPool pool = new ForkJoinPool();
    Deque<Socket> socketStack = new ConcurrentLinkedDeque<>();
    Deque<ServerBufferRequest> requestStack = new ConcurrentLinkedDeque<>();
    Deque<ServerBufferResponse> responseStack = new ConcurrentLinkedDeque<>();

    private static final Logger LOGGER = Logger.getLogger(Server.class.getName());

    private int port;
    public void setPort(int port) {
        if (port <= 0) throw new RuntimeException("Port должен быть целым числом больше нуля");
        this.port = port;
    }
    private final CollectionManager collectionManager;
    private ServerSocket serverSocket;
    public Server(int port,CollectionManager collectionManager){
        setPort(port);
        this.collectionManager = collectionManager;
    }

    public void serverRun(){
        try {
            LOGGER.info("Начинаю открытие серверного сокета для последующего соединения");
            openServerSocket();
            LOGGER.info("Сокет открыт");
            boolean processingStatus = true;
            LOGGER.info("Начинаю основную работу сервера");
            connectToClient();
            processClientRequest();
            stop();
            LOGGER.info("Работа сервера завершена");
        } catch (OpeningServerSocketException e) {
            LOGGER.warning(e.getMessage());
            LOGGER.warning("Сервер не может быть запущен!");
        }
    }
    private void openServerSocket() throws OpeningServerSocketException {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IllegalArgumentException e) {
            throw new OpeningServerSocketException("Порт" + port +  " находится за пределами возможных значений!");
        } catch (IOException e) {
            throw new OpeningServerSocketException("Произошла ошибка при попытке использовать порт" + port + "'!");
        }
    }
    private void connectToClient(){
        Thread acceptThread = new Thread(new AcceptConnectionTask(serverSocket, socketStack));
        acceptThread.start();
    }
    private boolean processClientRequest() {
        while (true) {
            if (!socketStack.isEmpty()) { // Проверяем, есть ли новые подключения в стеке
                Socket socket = socketStack.pop(); // Извлекаем одно подключение из стека
                pool.execute(new HandleConnect(socket,requestStack));
                System.out.println("Запущен обработчик подключения");
            }
            if(!requestStack.isEmpty()){
                ServerBufferRequest serverBufferRequest = requestStack.pop();
                System.out.println("Началась обработка строки " + serverBufferRequest.getRequest().getCommand());
                new Thread( new HandlerRequest(responseStack, serverBufferRequest, collectionManager)).start();
                System.out.println("Запрос обработан и передан на отправку");
            }
            if(!responseStack.isEmpty()){
                ServerBufferResponse serverBuffer = responseStack.pop();
                pool.execute(new OutputConnectTask(serverBuffer));
            }
        }
    }
    private void stop() {
        try {
            if (serverSocket == null)
                throw new ClosingSocketException("Невозможно завершить работу еще не запущенного сервера!");
            serverSocket.close();
        } catch (ClosingSocketException e) {
            LOGGER.warning(e.getMessage());
        } catch (IOException exception) {
            LOGGER.warning("Произошла ошибка при завершении работы сервера!");
        }
    }
}
