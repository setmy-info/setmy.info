package info.setmy.microservice.web.exception;

import info.setmy.microservice.exception.MicroServiceException;

public class MicroWebServiceException extends MicroServiceException {

    public MicroWebServiceException() {
        super();
    }

    public MicroWebServiceException(String message) {
        super(message);
    }

    public MicroWebServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public MicroWebServiceException(Throwable cause) {
        super(cause);
    }

    protected MicroWebServiceException(String message, Throwable cause,
                                       boolean enableSuppression,
                                       boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
