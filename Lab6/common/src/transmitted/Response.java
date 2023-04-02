package transmitted;

import java.io.Serializable;

public class Response implements Serializable {
    private ServerStatus status;
    private String responseBody;
    public Response(ServerStatus status, String responseBody){
        this.responseBody = responseBody;
        this.status = status;
    }

    public String getResponse() {
        return responseBody;
    }

    public ServerStatus getStatus() {
        return status;
    }
}
