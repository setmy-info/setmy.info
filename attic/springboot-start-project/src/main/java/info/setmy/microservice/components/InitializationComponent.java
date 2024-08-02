package info.setmy.microservice.components;

import info.setmy.microservice.properties.ExampleProperties;
import info.setmy.microservice.properties.RESTProperties;
import jakarta.annotation.PostConstruct;
import java.util.TimeZone;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class InitializationComponent implements InitializingBean {

    private final RESTProperties restProperties;

    private final ExampleProperties exampleProperties;

    @PostConstruct
    public void postConstruct() throws Exception {
        log.info("======== IS CONSTRUCTED ===========");
        if (restProperties.getDatesUTC()) {
            TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("======== PROPERTIES ARE SET ===========");
    }
}
