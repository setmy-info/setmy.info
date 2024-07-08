package info.setmy.microservice.beans;

import javax.sql.DataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties(LiquibaseProperties.class)
public class LiquibaseDatasourceBeans {

    @Bean
    public DataSource liquibaseDataSource(final DataSourceProperties liquibaseDataSourceProperties) {
        return liquibaseDataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    public SpringLiquibase liquibase(
            final DataSource liquibaseDataSource,// Liquibase can have DB with more rights
            final LiquibaseProperties liquibaseProperties) {
        final SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(liquibaseDataSource);
        liquibase.setChangeLog(liquibaseProperties.getChangeLog());
        liquibase.setContexts(liquibaseProperties.getContexts());
        liquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
        liquibase.setDropFirst(liquibaseProperties.isDropFirst());
        liquibase.setShouldRun(liquibaseProperties.isEnabled());
        return liquibase;
    }
}
