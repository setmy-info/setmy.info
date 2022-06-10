package info.setmy.microservice.beans;

import info.setmy.microservice.properties.CacheProperties;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.spring.starter.embedded.InfinispanCacheConfigurer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.infinispan.configuration.cache.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Component
public class CacheConfig {

    private final Logger log = LogManager.getLogger(this.getClass());

    @Bean
    public InfinispanCacheConfigurer cacheConfigurer(final CacheProperties cacheProperties) {
        return manager -> {
            final Configuration ispnConfig = new ConfigurationBuilder()
                    .expiration()
                    .lifespan(cacheProperties.getCacheTimeoutMillis())
                    .build();
            manager.defineConfiguration(cacheProperties.getCacheName(), ispnConfig);
        };
    }

    @Bean("cache")
    public Cache getCache(
            final CacheManager cacheManager,
            final CacheProperties cacheProperties
    ) {
        log.info("Getting cache: {}", cacheProperties.getCacheName());
        return cacheManager.getCache(cacheProperties.getCacheName());
    }
}
