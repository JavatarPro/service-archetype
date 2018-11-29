package ${package}.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public abstract class RestException extends RuntimeException {

    protected HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    protected String code = "500_InternalServerError";

    protected String devMessage;

    protected LocalDateTime dateTime;


    public RestException() {}

    public RestException(String message) {
        super(message);
    }

    public String getDevMessage() {
        return devMessage;
    }

    public void setDevMessage(String devMessage) {
        this.devMessage = devMessage;
    }

    public RestException withDevMessage(String devMessage) {
        this.devMessage = devMessage;
        return this;
    }

    public RestException withCode(String code) {
        this.code = code;
        return this;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public RestException withStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    public String getCode(){
        return code;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}