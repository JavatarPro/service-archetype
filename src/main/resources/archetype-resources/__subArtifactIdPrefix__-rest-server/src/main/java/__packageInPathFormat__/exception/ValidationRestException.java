package ${package}.exception;

import org.springframework.http.HttpStatus;

public class ValidationRestException extends RestException {

    public static final String ERROR_CODE = "422_ValidationRestException";

    public ValidationRestException() {
        initialize();
    }

    public ValidationRestException(String message) {
        super(message);
        initialize();
    }

    private void initialize() {
        withStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        withCode(ERROR_CODE);
    }
}
