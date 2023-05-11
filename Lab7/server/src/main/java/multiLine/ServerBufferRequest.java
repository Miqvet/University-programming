package multiLine;

import transmitted.Request;

import java.io.ObjectOutputStream;
import java.io.PrintWriter;

public class ServerBufferRequest {
    private ObjectOutputStream writer;
    private Request request;

    public ServerBufferRequest(ObjectOutputStream writer, Request request) {
        this.writer = writer;
        this.request = request;
    }

    public Request getRequest() {
        return request;
    }

    public void setSocket(ObjectOutputStream writer) {
        this.writer = writer;
    }

    public ObjectOutputStream getWriter() {
        return writer;
    }
    public void setString(Request request) {
        this.request = request;
    }
}
