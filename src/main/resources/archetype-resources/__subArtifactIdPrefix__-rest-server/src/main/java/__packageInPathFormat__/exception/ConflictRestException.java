package ${package}.exception;

import org.springframework.http.HttpStatus;

/**
 * This is generic exception for cases when BL conflicts with client request
 */
public class ConflictRestException extends RestException {

    public static final String ERROR_CODE = "409_ConflictRestException";

    public ConflictRestException() {
        initialize();
    }

    public ConflictRestException(String message) {
        super(message);
        initialize();
    }

    private void initialize() {
        withStatus(HttpStatus.CONFLICT);
        withCode(ERROR_CODE);
    }
}
