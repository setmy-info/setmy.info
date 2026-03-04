package info.setmy.microservice.web.component;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

import static info.setmy.microservice.web.constant.ErrorConstants.KEY;
import static info.setmy.microservice.web.constant.ErrorConstants.UNKNOWN_ERROR_KEY_VALUE;

@Component
public class CustomErrorAttributesComponent {

    public Map<String, Object> getErrorAttributes(WebRequest request) {
        Map<String, Object> errorAttributes = new LinkedHashMap<>();
        errorAttributes.put("timestamp", Instant.now());
        errorAttributes.put(KEY, UNKNOWN_ERROR_KEY_VALUE);
        return errorAttributes;
    }
}
