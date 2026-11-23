package info.setmy.business.logic.starter;

import info.setmy.business.logic.services.ExampleBusinessLogicService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * Spring layer of business-logic-starter: the plain Java singletons of the library become Spring beans. Spring does
 * not create new instances, it registers the ones the library created.
 */
@AutoConfiguration
public class BusinessLogicAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ExampleBusinessLogicService exampleBusinessLogicService() {
        return ExampleBusinessLogicService.getInstance();
    }
}
