package info.setmy.microservice.web.component;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

import static info.setmy.microservice.web.constant.ErrorConstants.KEY;
import static info.setmy.microservice.web.constant.ErrorConstants.UNKNOWN_ERROR_KEY_VALUE;

@Component
public class CustomErrorAttributesComponent extends DefaultErrorAttributes {

    public Map<String, Object> getErrorAttributes(WebRequest request) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(request, ErrorAttributeOptions.defaults());
        errorAttributes.put(KEY, UNKNOWN_ERROR_KEY_VALUE);
        return errorAttributes;
    }
}
