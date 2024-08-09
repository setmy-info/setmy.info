package info.setmy.microservice.web.component;

import info.setmy.microservice.web.exception.MicroWebServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

import static info.setmy.microservice.web.constant.ErrorConstants.UNKNOWN_ERROR_KEY_VALUE;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerComponent extends ResponseEntityExceptionHandler {

    private final static String KEY = "key";

    private final CustomErrorAttributesComponent customErrorAttributesComponent;

    @ExceptionHandler(MicroWebServiceException.class)
    public ResponseEntity<Map<String, Object>> handleMicroWebServiceException(MicroWebServiceException ex, WebRequest request) {
        final Map<String, Object> errorAttributes = customErrorAttributesComponent.getErrorAttributes(request);
        errorAttributes.put(KEY, ex.getKey());
        return new ResponseEntity<>(errorAttributes, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex, WebRequest request) {
        final Map<String, Object> errorAttributes = customErrorAttributesComponent.getErrorAttributes(request);
        errorAttributes.put(KEY, UNKNOWN_ERROR_KEY_VALUE);
        return new ResponseEntity<>(errorAttributes, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
