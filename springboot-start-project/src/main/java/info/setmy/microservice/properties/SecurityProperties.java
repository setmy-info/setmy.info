package info.setmy.microservice.properties;

import java.util.Optional;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Configuration("securityProperties")
@ConfigurationProperties(prefix = "system.http-security")
public class SecurityProperties {

    private Optional<Boolean> csrf;

    private Optional<Boolean> frameOptions;

    public Optional<Boolean> getCsrf() {
        return csrf;
    }

    public void setCsrf(Optional<Boolean> csrf) {
        this.csrf = csrf;
    }

    public Optional<Boolean> getFrameOptions() {
        return frameOptions;
    }

    public void setFrameOptions(Optional<Boolean> frameOptions) {
        this.frameOptions = frameOptions;
    }
}
