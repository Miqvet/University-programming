package multiLine;

import transmitted.Response;

import java.io.ObjectOutputStream;
import java.io.PrintWriter;

public class ServerBufferResponse {
    private ObjectOutputStream writer;
    private Response response;

    public ServerBufferResponse(ObjectOutputStream writer, Response response) {
        this.writer = writer;
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public void setWriter(ObjectOutputStream writer) {
        this.writer = writer;
    }

    public ObjectOutputStream getWriter() {
        return writer;
    }
    public void setResponse(Response response) {
        this.response = response;
    }
}
