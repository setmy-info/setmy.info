package info.setmy.microservice.beans;


import info.setmy.microservice.properties.BuildProperties;
import info.setmy.microservice.properties.ApiDocumentationProperties;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * http://localhost:8080/v2/api-docs
 *
 * http://localhost:8080/swagger-ui/index.html
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Component
public class ApiDocumentationConfig {

    @Bean
    Info apiInfo(final ApiDocumentationProperties apiDocumentation,
            final BuildProperties buildProperties) {
        return new Info()
                //.license(new License().name("Apache 2.0").url("http://springdoc.org"))
                .title(apiDocumentation.getTitle())
                .description(apiDocumentation.getDescription())
                .version(buildProperties.getVersion());
    }

    @Bean
    public OpenAPI openAPI(final Info apiInfo, final ApiDocumentationProperties apiDocumentation) {
        return new OpenAPI()
                .info(apiInfo)
                .externalDocs(new ExternalDocumentation()
                        .description(apiDocumentation.getDescription())
                        .url(apiDocumentation.getDocsUrl()));
    }
}
