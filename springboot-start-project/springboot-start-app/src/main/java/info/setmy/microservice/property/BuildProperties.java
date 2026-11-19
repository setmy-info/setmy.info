package info.setmy.microservice.property;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Build information, from application.yaml: Maven fills it in when the resources are filtered, the revision comes
 * from build.properties of buildnumber-maven-plugin. The values here are the defaults when nothing is set.
 */
@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "spring-boot-start-project.build")
@PropertySource(value = "classpath:build.properties", ignoreResourceNotFound = true)
public class BuildProperties {

    private String name = "spring-boot-project";

    private String version = "0.0.0-SNAPSHOT";

    private String timestamp = "01.01.1970 00:00";

    private String revision = "0";
}
