package transmitted;

import java.io.Serializable;

public enum ServerStatus implements Serializable {
    OK,
    ERROR,
    SERVER_DISABLED,
    REQUEST_ERROR;

    private static final long serialVersionUID = 4L;
}
