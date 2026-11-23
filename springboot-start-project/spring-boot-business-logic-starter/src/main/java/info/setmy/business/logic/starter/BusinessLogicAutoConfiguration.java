package info.setmy.business.logic.starter;

import info.setmy.business.logic.services.ExampleBusinessLogicService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class BusinessLogicAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ExampleBusinessLogicService exampleBusinessLogicService() {
        return ExampleBusinessLogicService.getInstance();
    }
}
