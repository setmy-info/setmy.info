package info.setmy.microservice.web.controller.advice;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class CustomErrorAttributesComponent extends DefaultErrorAttributes {

    public Map<String, Object> getErrorAttributes(WebRequest request) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(request, ErrorAttributeOptions.defaults());
        errorAttributes.put("microservice_message", "Error about something.");
        return errorAttributes;
    }
}
