package info.setmy.microservice.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Configuration("buildProperties")
@PropertySource("classpath:build.properties")
@Getter
@Setter
public class BuildProperties {

    @Value("${name}")
    private String name;

    @Value("${version}")
    private String version;

    @Value("${timestamp}")
    private String timestamp;

    @Value("${revision}")
    private String revision;
}
