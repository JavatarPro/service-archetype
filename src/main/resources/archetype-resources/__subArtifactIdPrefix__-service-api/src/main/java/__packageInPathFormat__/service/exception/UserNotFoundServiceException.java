package ${package}.service.exception;

public class UserNotFoundServiceException extends Exception {

    public UserNotFoundServiceException() {
    }

    public UserNotFoundServiceException(String message) {
        super(message);
    }

    public UserNotFoundServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
