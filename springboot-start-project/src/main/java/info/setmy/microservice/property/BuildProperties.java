package info.setmy.microservice.property;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@ToString
@PropertySource(value = "classpath:build.properties", ignoreResourceNotFound = true)
public class BuildProperties {

    @Value("${name}:spring-boot-project")
    private String name;

    @Value("${version:0.0.0-SNAPSHOT}")
    private String version;

    @Value("${timestamp:01.01.1970 00:00}")
    private String timestamp;

    @Value("${revision:0}")
    private String revision;
}
