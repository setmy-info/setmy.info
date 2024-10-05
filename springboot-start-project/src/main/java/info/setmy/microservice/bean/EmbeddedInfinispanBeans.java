package info.setmy.microservice.bean;

import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.jboss.marshalling.core.JBossUserMarshaller;
import org.infinispan.spring.starter.embedded.InfinispanCacheConfigurer;
import org.infinispan.spring.starter.embedded.InfinispanGlobalConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
import static org.infinispan.configuration.cache.CacheMode.LOCAL;
import static org.infinispan.eviction.EvictionType.COUNT;
import org.infinispan.spring.starter.embedded.InfinispanGlobalConfigurationCustomizer;
*/
@Configuration
public class EmbeddedInfinispanBeans {

    @Bean
    public InfinispanCacheConfigurer cacheConfigurer() {
        return manager -> {
            final org.infinispan.configuration.cache.Configuration ispnConfig = new ConfigurationBuilder()
                .clustering()
                .cacheMode(CacheMode.LOCAL)
                .build();

            manager.defineConfiguration("exampleCache", ispnConfig);
        };
    }

    @Bean
    public GlobalConfiguration globalConfiguration() {
        return GlobalConfigurationBuilder
            .defaultClusteredBuilder()
            .transport()
            .defaultTransport()
            .clusterName("cluster")
            .jmx()//.disable()
            .enabled(true)
            .domain("microserviceDomainName")
            .serialization()
            .marshaller(new JBossUserMarshaller())
            .build();
    }

    // Tests execution: Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'defaultCacheManager' defined in class path resource [org/infinispan/spring/starter/embedded/InfinispanEmbeddedAutoConfiguration.class]: Failed to instantiate [org.infinispan.manager.DefaultCacheManager]: Factory method 'defaultCacheManager' threw exception with message: ISPN000034: The 'org.infinispan' JMX domain is already in use.
    @Bean
    public InfinispanGlobalConfigurationCustomizer globalCustomizer() {
        return builder -> builder.jmx().disable();
    }

    /*
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
    */
}
