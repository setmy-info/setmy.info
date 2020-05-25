package info.setmy.microservice.beans;

import info.setmy.microservice.properties.BuildProperties;
import info.setmy.microservice.properties.SwaggerProperties;
import java.time.LocalDate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * http://localhost:8080/v2/api-docs
 *
 * http://localhost:8080/swagger-ui.html#
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Component
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    ApiInfo getApiInfo(
            final SwaggerProperties swaggerProperties,
            final BuildProperties buildProperties
    ) {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .version(buildProperties.getVersion())
                .build();
    }

    @Bean
    public Docket getDocket(
            final SwaggerProperties swaggerProperties,
            final ApiInfo apiInfo
    ) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .enable(swaggerProperties.getEnabled())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(
                        swaggerProperties.getUseDefaultResponseMessages()
                )
                .enableUrlTemplating(
                        swaggerProperties.getEnableUrlTemplating()
                );

    }

    @Bean
    UiConfiguration getUiConfiguration(
            final SwaggerProperties swaggerProperties
    ) {
        return UiConfigurationBuilder.builder()
                .deepLinking(swaggerProperties.getDeepLinking())
                .displayOperationId(swaggerProperties.getDisplayOperationId())
                .defaultModelsExpandDepth(
                        swaggerProperties.getDefaultModelsExpandDepth()
                )
                .defaultModelExpandDepth(
                        swaggerProperties.getDefaultModelExpandDepth()
                )
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(
                        swaggerProperties.getDisplayRequestDuration()
                )
                .docExpansion(DocExpansion.NONE)
                .filter(swaggerProperties.getFilter())
                .maxDisplayedTags(swaggerProperties.getMaxDisplayedTags())
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(swaggerProperties.getShowExtensions())
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(
                        UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS
                )
                .validatorUrl(null)
                .build();
    }

}
