package info.setmy.microservice;

/*
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.spring.starter.remote.InfinispanRemoteCacheCustomizer;
import org.infinispan.spring.starter.remote.InfinispanRemoteConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
*/

//@Configuration
public class RemoteInfinispanBeans {

    /*
    @Bean
    public InfinispanRemoteConfigurer infinispanRemoteConfigurer() {
        return () -> new ConfigurationBuilder()
            .addServer()
            .host("127.0.0.1")
            .port(11222)
            .build();
    }

    @Bean
    public org.infinispan.client.hotrod.configuration.Configuration customConfiguration() {
        return new ConfigurationBuilder()
            .addServer()
            .host("127.0.0.1")
            .port(11222)
            .build();
    }

    @Bean
    public InfinispanRemoteCacheCustomizer customizer() {
        return b -> b.tcpKeepAlive(false);
    }
    */
}
