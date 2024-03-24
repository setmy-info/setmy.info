package info.setmy.microservice;

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
@PropertySource("classpath:build.properties")
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
