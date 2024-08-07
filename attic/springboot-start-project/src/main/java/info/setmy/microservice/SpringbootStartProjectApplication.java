package info.setmy.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableAsync
public class SpringbootStartProjectApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SpringbootStartProjectApplication.class, args);
    }
}
