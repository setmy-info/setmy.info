package info.setmy.microservice.config;

import info.setmy.microservice.properties.CacheProperties;
import org.infinispan.Cache;
import org.infinispan.manager.EmbeddedCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Configuration
public class CahceConfig {

    /*@Bean
    public Cache getCache(
            //final EmbeddedCacheManager cacheManager,
            final CacheProperties cacheProperties
    ) {
        return cacheManager.getCache(cacheProperties.getInfinispanCacheName());
    }*/
}
