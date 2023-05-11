package transmitted;

import java.io.Serial;
import java.io.Serializable;

public class Response implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;
    private final ServerStatus status;
    private final String responseBody;
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
