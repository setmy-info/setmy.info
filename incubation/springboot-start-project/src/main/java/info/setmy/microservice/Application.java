package info.setmy.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;

@EnableScheduling
@SpringBootApplication
/*@ComponentScan(basePackages = {
    "info.setmy.microservice",
    "info.setmy.microservice.dal",
    "info.setmy.microservice.dal"
})*/
public class Application {

    public static void main(final String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(Application.class, args);
    }
}
