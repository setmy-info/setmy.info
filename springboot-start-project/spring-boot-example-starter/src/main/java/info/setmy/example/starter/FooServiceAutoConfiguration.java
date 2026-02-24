package info.setmy.example.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FooServiceAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public FooService fooService() {
        return FooService.getInstance();
    }
}
