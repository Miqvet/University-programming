package exceptions;

/**
 * Is throwed when there's an error while connecting somewhere.
 */
public class ConnectionException extends Exception {
    public ConnectionException(String message) {
        super(message);
    }

}
