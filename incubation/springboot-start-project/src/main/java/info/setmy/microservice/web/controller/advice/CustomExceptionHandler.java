package info.setmy.microservice.web.controller.advice;

import info.setmy.microservice.web.exception.MicroWebServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private final CustomErrorAttributesComponent customErrorAttributesComponent;

    @ExceptionHandler(MicroWebServiceException.class)
    public ResponseEntity<Map<String, Object>> handleMicroWebServiceException(MicroWebServiceException ex, WebRequest request) {
        final String errorMessage = "Found WEB exception.";
        final Map<String, Object> errorAttributes = customErrorAttributesComponent.getErrorAttributes(request);
        errorAttributes.put("microservice_message", errorMessage);
        return new ResponseEntity<>(errorAttributes, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex, WebRequest request) {
        final String errorMessage = "Found exception.";
        final Map<String, Object> errorAttributes = customErrorAttributesComponent.getErrorAttributes(request);
        errorAttributes.put("microservice_message", errorMessage);
        return new ResponseEntity<>(errorAttributes, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
