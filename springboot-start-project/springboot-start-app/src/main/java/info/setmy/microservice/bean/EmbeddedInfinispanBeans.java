package info.setmy.microservice.bean;

import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.spring.starter.embedded.InfinispanCacheConfigurer;
import org.infinispan.spring.starter.embedded.InfinispanGlobalConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Embedded Infinispan, the Spring cache manager of {@code @Cacheable}.
 * <p>
 * LOCAL: every pod has its own cache, a change made through one pod is seen by the others after the lifespan only.
 */
@Configuration
public class EmbeddedInfinispanBeans {

    /**
     * At most this many entries, the least recently used are evicted.
     */
    private static final long EXAMPLE_CACHE_MAX_COUNT = 1000;

    /**
     * An entry is removed this long after it was written.
     */
    private static final long EXAMPLE_CACHE_LIFESPAN_MINUTES = 10;

    /**
     * An entry not read for this long is removed before the lifespan.
     */
    private static final long EXAMPLE_CACHE_MAX_IDLE_MINUTES = 5;

    @Bean
    public InfinispanCacheConfigurer cacheConfigurer() {
        return manager -> {
            final org.infinispan.configuration.cache.Configuration ispnConfig = new ConfigurationBuilder()
                .clustering()
                .cacheMode(CacheMode.LOCAL)
                .memory()
                .maxCount(EXAMPLE_CACHE_MAX_COUNT)
                .expiration()
                .lifespan(EXAMPLE_CACHE_LIFESPAN_MINUTES, TimeUnit.MINUTES)
                .maxIdle(EXAMPLE_CACHE_MAX_IDLE_MINUTES, TimeUnit.MINUTES)
                .build();

            manager.defineConfiguration("exampleCache", ispnConfig);
        };
    }

    @Bean
    public InfinispanGlobalConfigurationCustomizer globalCustomizer() {
        return builder -> builder.jmx().disable();
    }
}
