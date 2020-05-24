package info.setmy.microservice.config;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Configuration
public class DozerConfiguration {

    /*@Bean("dozerBeanMapper")
    public Mapper springLiquibase() {
        Mapper mapper = DozerBeanMapperBuilder.create()
                .withMappingFiles("dozer.xml")
                .build();
        return mapper;
    }*/
}
