package info.setmy.microservice.web.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceUnavailableException extends MicroWebServiceException {

    private static final int ERROR_CODE = 503;

    public ServiceUnavailableException(String message) {
        super(message);
    }

    public ServiceUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }

    protected ServiceUnavailableException(String message, Throwable cause,
                                          boolean enableSuppression,
                                          boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public int getErrorCode() {
        return ERROR_CODE;
    }
}
