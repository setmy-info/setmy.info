package info.setmy.microservice.beans;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
@Configuration
@EnableJpaRepositories(value = {"info.setmy.microservice.dao"})
@EnableTransactionManagement
public class DatasourceBeans {

    /*
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("dataSource")
    public DataSource dataSource(final DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }
    
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("dataSource") final DataSource dataSource,
            @Qualifier("jpaAdapter") final HibernateJpaVendorAdapter jpaAdapter,
            @Qualifier("loadTimeWeaver") final InstrumentationLoadTimeWeaver loadTimeWeaver) {
        final LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan(new String[]{"info.setmy.microservice.models"});
        entityManagerFactory.setPersistenceUnitName("default");
        entityManagerFactory.setJpaVendorAdapter(jpaAdapter);
        entityManagerFactory.setPersistenceXmlLocation("classpath*:META-INF/persistence.xml");
        //entityManagerFactory.setLoadTimeWeaver(loadTimeWeaver);
        return entityManagerFactory;
    }
     
    @Bean
    public HibernateJpaVendorAdapter jpaAdapter() {
        //<!--property name="databasePlatform" value="org.hibernate.dialect.Oracle10gDialect"/-->
        //<!--property name="databasePlatform" value="org.hibernate.dialect.PostgreSQLDialect"/-->
        //<!--property name="databasePlatform" value="org.hibernate.dialect.HSQLDialect"/-->
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public InstrumentationLoadTimeWeaver loadTimeWeaver() {
        return new InstrumentationLoadTimeWeaver();
    }

    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
     */
    @Bean
    public HibernateJpaVendorAdapter jpaAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
