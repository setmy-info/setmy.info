package info.setmy.crawler.elt.services;

import info.setmy.crawler.elt.models.DataConnectionTraversal;
import jakarta.inject.Inject;
import jakarta.persistence.Persistence;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class HibernateService {

    public DataConnectionTraversal fillHibernateProperties(final DataConnectionTraversal dataConnectionTraversal) {
        final Map<String, Object> hibernateProperties = new HashMap<>();
        hibernateProperties.put("hibernate.connection.datasource", dataConnectionTraversal.dataSource());
        hibernateProperties.put("hibernate.hikari.dataSource", dataConnectionTraversal.dataSource());
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProperties.put("hibernate.hbm2ddl.auto", "validate"); // validate, update, create, create-drop
        hibernateProperties.put("hibernate.show_sql", "true");
        hibernateProperties.put("hibernate.format_sql", "true");
        hibernateProperties.put("hibernate.use_sql_comments", "true");
        hibernateProperties.put("hibernate.jdbc.batch_size", "20");
        hibernateProperties.put("hibernate.order_inserts", "true");
        hibernateProperties.put("hibernate.order_updates", "true");
        hibernateProperties.put("hibernate.batch_fetch_style", "DYNAMIC");
        hibernateProperties.put("hibernate.hikari.minimumIdle", "2");
        hibernateProperties.put("hibernate.hikari.maximumPoolSize", "10");
        hibernateProperties.put("hibernate.hikari.idleTimeout", "30000");
        hibernateProperties.put("hibernate.hikari.connectionTimeout", "20000");
        hibernateProperties.put("hibernate.hikari.maxLifetime", "120000");
        hibernateProperties.put("jakarta.persistence.nonJtaDataSource", dataConnectionTraversal.dataSource());
        return dataConnectionTraversal.toBuilder()
            .hibernateProperties(hibernateProperties)
            .build();
    }

    public DataConnectionTraversal fillEntityManagerFactory(final DataConnectionTraversal dataConnectionTraversal) {
        return dataConnectionTraversal.toBuilder()
            .entityManagerFactory(
                Persistence.createEntityManagerFactory(
                    dataConnectionTraversal.persistenceUnitName(),
                    dataConnectionTraversal.hibernateProperties()
                )
            )
            .build();
    }

    public DataConnectionTraversal fillEntityManager(final DataConnectionTraversal dataConnectionTraversal) {
        return dataConnectionTraversal.toBuilder()
            .entityManager(dataConnectionTraversal.entityManagerFactory().createEntityManager())
            .build();
    }
}
