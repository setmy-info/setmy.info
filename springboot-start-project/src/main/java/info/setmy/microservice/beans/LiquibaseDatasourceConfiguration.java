package info.setmy.microservice.beans;

import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Configuration
@EnableTransactionManagement
public class LiquibaseDatasourceConfiguration {

    @Bean
    @ConfigurationProperties("liquibase-data-source")
    public DataSourceProperties liquibaseDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("liquibase-data-source")
    public DataSource liquibaseDataSource(final DataSourceProperties liquibaseDataSourceProperties) {
        return liquibaseDataSourceProperties.initializeDataSourceBuilder().build();
    }
}
