package info.setmy.microservice.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Configuration("featuresProperties")
@ConfigurationProperties(prefix = "features", ignoreUnknownFields = false)
public class FeaturesProperties {

    private boolean foo;

    public boolean isFoo() {
        return foo;
    }

    public void setFoo(boolean foo) {
        this.foo = foo;
    }
};
