package info.setmy.microservice.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Configuration("swaggerProperties")
@ConfigurationProperties(prefix = "swagger")
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

    public Boolean getDeepLinking() {
        return deepLinking;
    }

    public void setDeepLinking(Boolean deepLinking) {
        this.deepLinking = deepLinking;
    }

    public Integer getDefaultModelExpandDepth() {
        return defaultModelExpandDepth;
    }

    public void setDefaultModelExpandDepth(Integer defaultModelExpandDepth) {
        this.defaultModelExpandDepth = defaultModelExpandDepth;
    }

    public Integer getDefaultModelsExpandDepth() {
        return defaultModelsExpandDepth;
    }

    public void setDefaultModelsExpandDepth(Integer defaultModelsExpandDepth) {
        this.defaultModelsExpandDepth = defaultModelsExpandDepth;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDisplayOperationId() {
        return displayOperationId;
    }

    public void setDisplayOperationId(Boolean displayOperationId) {
        this.displayOperationId = displayOperationId;
    }

    public Boolean getDisplayRequestDuration() {
        return displayRequestDuration;
    }

    public void setDisplayRequestDuration(Boolean displayRequestDuration) {
        this.displayRequestDuration = displayRequestDuration;
    }

    public Boolean getEnableUrlTemplating() {
        return enableUrlTemplating;
    }

    public void setEnableUrlTemplating(Boolean enableUrlTemplating) {
        this.enableUrlTemplating = enableUrlTemplating;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getFilter() {
        return filter;
    }

    public void setFilter(Boolean filter) {
        this.filter = filter;
    }

    public Integer getMaxDisplayedTags() {
        return maxDisplayedTags;
    }

    public void setMaxDisplayedTags(Integer maxDisplayedTags) {
        this.maxDisplayedTags = maxDisplayedTags;
    }

    public Boolean getShowExtensions() {
        return showExtensions;
    }

    public void setShowExtensions(Boolean showExtensions) {
        this.showExtensions = showExtensions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getUseDefaultResponseMessages() {
        return useDefaultResponseMessages;
    }

    public void setUseDefaultResponseMessages(Boolean useDefaultResponseMessages) {
        this.useDefaultResponseMessages = useDefaultResponseMessages;
    }
}
