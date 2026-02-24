package info.setmy.microservice.bean;

import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.spring.starter.embedded.InfinispanCacheConfigurer;
import org.infinispan.spring.starter.embedded.InfinispanGlobalConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public InfinispanGlobalConfigurationCustomizer globalCustomizer() {
        return builder -> builder.jmx().disable();
    }
}
