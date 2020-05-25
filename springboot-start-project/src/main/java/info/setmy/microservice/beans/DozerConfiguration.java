package info.setmy.microservice.beans;

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

    @Bean("dozerBeanMapper")
    public Mapper springLiquibase() {
        return DozerBeanMapperBuilder.create()
                .withMappingFiles("dozer.xml")
                .build();
    }
}
