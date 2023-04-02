package exceptions;

/**
 * Is throwed when there's an error while connecting somewhere.
 */
public class ConnectionErrorException extends Exception {
    public ConnectionErrorException(String message) {
        super(message);
    }

}
