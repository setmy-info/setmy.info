package info.setmy.microservice.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
@Getter
@Setter
@Configuration("apiDocumentation")
@ConfigurationProperties(prefix = "api-documentation")
public class ApiDocumentationProperties {

    private String title;
    private String description;
    private String docsUrl;
}
