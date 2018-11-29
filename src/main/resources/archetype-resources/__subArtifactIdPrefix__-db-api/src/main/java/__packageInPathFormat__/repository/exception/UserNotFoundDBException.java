package ${package}.repository.exception;

public class UserNotFoundDBException extends Exception {

    public UserNotFoundDBException() {
    }

    public UserNotFoundDBException(String message) {
        super(message);
    }

    public UserNotFoundDBException(String message, Throwable cause) {
        super(message, cause);
    }
}
