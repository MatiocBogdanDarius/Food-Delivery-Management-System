package exceptions;

public class IncompatiblePasswordsException extends Exception {
    public IncompatiblePasswordsException(String message) {
        super(message);
    }
}
