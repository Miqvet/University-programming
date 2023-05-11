package multiLine;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Deque;

public class AcceptConnectionTask implements Runnable{
    private final ServerSocket server;
    private final Deque<Socket> socketStack;

    public AcceptConnectionTask(ServerSocket server, Deque<Socket> socketStack) {
        this.server = server;
        this.socketStack = socketStack;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = server.accept();
                socketStack.push(socket); // Добавляем новое подключение в стек
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
