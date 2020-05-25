package info.setmy.microservice.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Configuration("swaggerProperties")
@ConfigurationProperties(prefix = "swagger")
@Getter
@Setter
public class SwaggerProperties {

    private Boolean deepLinking;

    private Integer defaultModelExpandDepth;

    private Integer defaultModelsExpandDepth;

    private String description;

    private Boolean displayOperationId;

    private Boolean displayRequestDuration;

    private Boolean enableUrlTemplating;

    private Boolean enabled = false;

    private Boolean filter;

    private Integer maxDisplayedTags;

    private Boolean showExtensions;

    private String title;

    private Boolean useDefaultResponseMessages;
}
