package info.setmy.microservice.beans;

import info.setmy.microservice.aspects.RESTExampleAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
@Configuration
@EnableAspectJAutoProxy
public class AOPBeans {

    @Bean
    public RESTExampleAspect exampleAspect() {
        return new RESTExampleAspect();
    }
}
