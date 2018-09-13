package info.setmy.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@SpringBootApplication
@EnableScheduling
public class SpringbootStartProjectApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SpringbootStartProjectApplication.class, args);
    }
}
