package exceptions;

/**
 * Is throwed when server socket can't be opened.
 */
public class OpeningServerSocketException extends Exception {
    public OpeningServerSocketException(String message) {
        super(message);
    }
}
