package info.setmy.microservice.exceptions;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class MicroServiceException extends RuntimeException {

    public MicroServiceException() {
        super();
    }

    public MicroServiceException(String message) {
        super(message);
    }

    public MicroServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public MicroServiceException(Throwable cause) {
        super(cause);
    }

    protected MicroServiceException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
