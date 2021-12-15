package info.setmy.microservice.components;

import info.setmy.microservice.properties.ExampleProperties;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class InitializationComponent implements InitializingBean {

    private final ExampleProperties exampleProperties;

    @PostConstruct
    public void preDestroy() throws Exception {
        log.info("======== IS CONSTRUCTED ===========");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("======== PROPERTIES ARE SET ===========");
    }
}
