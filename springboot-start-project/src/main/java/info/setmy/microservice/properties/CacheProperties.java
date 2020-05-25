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

    private String cacheName;

    private Long cacheTimeoutMillis;

    public Long getCacheTimeoutMillis() {
        return cacheTimeoutMillis;
    }

    public void setCacheTimeoutMillis(Long cacheTimeoutMillis) {
        this.cacheTimeoutMillis = cacheTimeoutMillis;
    }

    public String getCacheName() {
        return cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }
}
