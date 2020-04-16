package info.setmy.microservice.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Configuration
@EnableJpaRepositories(value = {"info.setmy.microservice.dao"})
@EnableTransactionManagement
public class DatasourceConfiguration {

    @Primary
    @Bean(name = "dataSourceProperties")
    @ConfigurationProperties("data-source")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties("data-source")
    public DataSource dataSource(
            @Qualifier("dataSourceProperties")
            final DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            final EntityManagerFactoryBuilder builder,
            @Qualifier("dataSource") final DataSource dataSource,
            @Qualifier("jpaAdapter") final HibernateJpaVendorAdapter jpaAdapter,
            @Qualifier("loadTimeWeaver") final InstrumentationLoadTimeWeaver loadTimeWeaver) {
        final LocalContainerEntityManagerFactoryBean entityManagerFactory = builder
                .dataSource(dataSource)
                .packages(new String[]{"info.setmy.microservice.models"})
                .persistenceUnit("default")// persistenceUnitName ?
                .build();
        entityManagerFactory.setJpaVendorAdapter(jpaAdapter);
        entityManagerFactory.setPersistenceXmlLocation("classpath*:META-INF/persistence.xml");
        entityManagerFactory.setLoadTimeWeaver(loadTimeWeaver);
        return entityManagerFactory;
    }

    @Bean(name = "jpaAdapter")
    public HibernateJpaVendorAdapter jpaAdapter() {
        /*<!--property name="databasePlatform" value="org.hibernate.dialect.Oracle10gDialect"/-->
        <!--property name="databasePlatform" value="org.hibernate.dialect.PostgreSQLDialect"/-->
        <!--property name="databasePlatform" value="org.hibernate.dialect.HSQLDialect"/-->*/
        return new HibernateJpaVendorAdapter();
    }

    @Bean(name = "loadTimeWeaver")
    public InstrumentationLoadTimeWeaver loadTimeWeaver() {
        return new InstrumentationLoadTimeWeaver();
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
