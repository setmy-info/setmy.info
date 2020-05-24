package info.setmy.microservice.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Configuration("cacheProperties")
@ConfigurationProperties(prefix = "system.cache")
public class CacheProperties {

    private String infinispanCacheName;

    public String getInfinispanCacheName() {
        return infinispanCacheName;
    }

    public void setInfinispanCacheName(String infinispanCacheName) {
        this.infinispanCacheName = infinispanCacheName;
    }
}
