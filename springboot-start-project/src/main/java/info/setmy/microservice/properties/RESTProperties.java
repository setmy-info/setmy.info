package info.setmy.microservice.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Configuration("restProperties")
@ConfigurationProperties(prefix = "system.rest")
public class RESTProperties {

    private Boolean datesUTC;

    public Boolean getDatesUTC() {
        return datesUTC;
    }

    public void setDatesUTC(Boolean datesUTC) {
        this.datesUTC = datesUTC;
    }
}
