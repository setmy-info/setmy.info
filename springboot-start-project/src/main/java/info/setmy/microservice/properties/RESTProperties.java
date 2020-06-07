package info.setmy.microservice.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Configuration("restProperties")
@ConfigurationProperties(prefix = "system.rest")
@Getter
@Setter
public class RESTProperties {

    private Boolean datesUTC;
}
