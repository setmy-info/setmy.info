package info.setmy.microservice.exception;

public class ExampleRollbackException extends MicroServiceException {

    public ExampleRollbackException() {
        super();
    }

    public ExampleRollbackException(String message) {
        super(message);
    }

    public ExampleRollbackException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExampleRollbackException(Throwable cause) {
        super(cause);
    }

    protected ExampleRollbackException(String message, Throwable cause,
                                       boolean enableSuppression,
                                       boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
