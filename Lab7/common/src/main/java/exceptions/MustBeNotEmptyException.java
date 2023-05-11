package exceptions;

/**
 * Is throwed when something must be not empty.
 */
public class MustBeNotEmptyException extends Exception {
    public MustBeNotEmptyException(String message) {
        super(message);
    }
}
