package info.setmy.microservice.config;

import java.util.Arrays;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
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
        final DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList("dozer.xml"));
        return mapper;
    }
}
