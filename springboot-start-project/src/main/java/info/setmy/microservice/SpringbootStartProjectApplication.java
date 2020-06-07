package info.setmy.microservice;

import info.setmy.microservice.properties.RESTProperties;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@SpringBootApplication
@EnableScheduling
@EnableCaching
public class SpringbootStartProjectApplication {

    @Autowired
    RESTProperties restProperties;

    @PostConstruct
    void started() {
        if (restProperties.getDatesUTC()) {
            TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
        }
    }

    public static void main(final String[] args) {
        SpringApplication.run(SpringbootStartProjectApplication.class, args);
    }
}
