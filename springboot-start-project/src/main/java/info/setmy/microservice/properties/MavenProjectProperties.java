package info.setmy.microservice.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Configuration("mavenProjectProperties")
@ConfigurationProperties(prefix = "maven.project")
@Getter
@Setter
public class MavenProjectProperties {

    private String groupId;

    private String artifactId;
}
