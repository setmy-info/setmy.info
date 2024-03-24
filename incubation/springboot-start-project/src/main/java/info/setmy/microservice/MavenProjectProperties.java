package info.setmy.microservice;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "maven.project")
public class MavenProjectProperties {

    private String groupId;

    private String artifactId;
}
