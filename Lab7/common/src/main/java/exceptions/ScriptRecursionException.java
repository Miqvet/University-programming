package exceptions;

/**
 * Is throwed when script is infinitely recursive.
 */
public class ScriptRecursionException extends Exception {
    public ScriptRecursionException(String message) {
        super(message);
    }
}
