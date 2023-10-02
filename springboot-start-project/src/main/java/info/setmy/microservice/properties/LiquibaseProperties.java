package info.setmy.microservice.properties;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LiquibaseProperties {

    @Bean
    @ConfigurationProperties("liquibase-data-source")
    public DataSourceProperties liquibaseDataSourceProperties() {
        return new DataSourceProperties();
    }
}
