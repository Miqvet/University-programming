package multiLine;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.concurrent.RecursiveAction;

public class OutputConnectTask extends RecursiveAction {

    private  ServerBufferResponse serverBufferResponse;

    public OutputConnectTask(ServerBufferResponse serverBuffer){
        this.serverBufferResponse = serverBuffer;
    }
    public void compute(){
        ObjectOutputStream writer = serverBufferResponse.getWriter();
        try {
            writer.writeObject(serverBufferResponse.getResponse());
            writer.flush();
            System.out.println("Строка отправлена");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
