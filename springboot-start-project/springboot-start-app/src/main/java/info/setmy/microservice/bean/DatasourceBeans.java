package info.setmy.microservice.bean;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.liquibase.autoconfigure.LiquibaseDataSource;
import org.springframework.boot.liquibase.autoconfigure.LiquibaseProperties;
import org.springframework.boot.quartz.autoconfigure.QuartzDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * The schema owner datasource, from spring.liquibase.* (url, user, password, driver-class-name).
 * <p>
 * Used by Liquibase for the migrations and by Quartz to create its tables (spring.quartz.jdbc.initialize-schema),
 * because the application user (spring.datasource.*) has rights on rows only. The default datasource of the
 * application stays the one Spring Boot creates from spring.datasource.*: defaultCandidate = false keeps this bean
 * out of the injection of a plain DataSource and out of the back-off of the Spring Boot DataSource auto-configuration.
 */
@Configuration
@EnableConfigurationProperties(LiquibaseProperties.class)
public class DatasourceBeans {

    @Bean(defaultCandidate = false)
    @LiquibaseDataSource
    @QuartzDataSource
    public DataSource ownerDataSource(final LiquibaseProperties properties) {
        return DataSourceBuilder.create()
            .url(properties.getUrl())
            .username(properties.getUser())
            .password(properties.getPassword())
            .driverClassName(properties.getDriverClassName())
            .build();
    }
}
