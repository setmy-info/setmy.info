package info.setmy.microservice.bean;

import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.spring.starter.embedded.InfinispanCacheConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.infinispan.configuration.cache.CacheMode.LOCAL;
import static org.infinispan.eviction.EvictionType.COUNT;
import org.infinispan.spring.starter.embedded.InfinispanGlobalConfigurationCustomizer;

@Configuration
public class EmbeddedInfinispanBeans {

    @Bean
    public org.infinispan.configuration.cache.Configuration exampleCacheConfiguration() {
        return new ConfigurationBuilder()
                .jmxStatistics().disable()
                .clustering()
                .cacheMode(LOCAL)
                .build();
    }

    @Bean
    public org.infinispan.configuration.cache.Configuration smallCacheConfiguration(
            org.infinispan.configuration.cache.Configuration exampleCacheConfiguration
    ) {
        return new ConfigurationBuilder()
                .read(exampleCacheConfiguration)
                .memory().size(1000L)
                .memory().evictionType(COUNT)
                .build();
    }

    @Bean
    public org.infinispan.configuration.cache.Configuration largeCacheConfiguration(
            org.infinispan.configuration.cache.Configuration exampleCacheConfiguration
    ) {
        return new ConfigurationBuilder()
                .read(exampleCacheConfiguration)
                .memory().size(2000L)
                .build();
    }

    @Bean
    public InfinispanCacheConfigurer cacheConfigurer(
            org.infinispan.configuration.cache.Configuration exampleCacheConfiguration,
            org.infinispan.configuration.cache.Configuration smallCacheConfiguration,
            org.infinispan.configuration.cache.Configuration largeCacheConfiguration
    ) {
        return manager -> {
            manager.defineConfiguration("exampleCache", exampleCacheConfiguration);
            manager.defineConfiguration("smallCache", smallCacheConfiguration);
            manager.defineConfiguration("largeCache", largeCacheConfiguration);
        };
    }

    @Bean
    public InfinispanGlobalConfigurationCustomizer globalCustomizer() {
        return builder -> builder.jmx().disable();
    }
}
