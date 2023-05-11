package exceptions;

/**
 * Is throwed when socket isn't opened yet, but program tries to close it.
 */
public class ClosingSocketException extends Exception {
    public ClosingSocketException(String message) {
        super(message);
    }
}
