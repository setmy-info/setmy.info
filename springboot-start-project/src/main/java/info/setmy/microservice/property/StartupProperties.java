package info.setmy.microservice.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring-boot-start-project.startup"/*, ignoreUnknownFields = false*/)
public class StartupProperties {

    private String timeZone;
}
