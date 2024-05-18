package info.setmy.microservice.web.exception;

import info.setmy.microservice.exception.MicroServiceException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@NoArgsConstructor(access = PRIVATE)
public abstract class MicroWebServiceException extends MicroServiceException {

    public MicroWebServiceException(String message) {
        super(message);
    }

    public MicroWebServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    protected MicroWebServiceException(String message, Throwable cause,
                                       boolean enableSuppression,
                                       boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getKey() {
        return getMessage();
    }

    public abstract int getErrorCode();
}
