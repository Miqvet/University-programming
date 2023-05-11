package multiLine;


import transmitted.Request;

import java.io.*;
import java.net.Socket;
import java.util.Deque;
import java.util.concurrent.RecursiveAction;

public class HandleConnect extends RecursiveAction {
    private Socket socket;
    private final Deque<ServerBufferRequest> requestStack;
    public HandleConnect(Socket socket, Deque<ServerBufferRequest> requestStack){
        this.socket = socket;
        this.requestStack = requestStack;
    }
    public void compute(){
        Request userRequest = null;
        try(ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream())){
            while(true){
                userRequest = (Request) reader.readObject();
                System.out.println(Thread.currentThread().getName());
                if(userRequest.getCommand().equals("exit")){
                    System.out.println("Закрываю сокет и разрываю соединение");
                    break;
                }
                requestStack.push(new ServerBufferRequest(writer,userRequest));
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
