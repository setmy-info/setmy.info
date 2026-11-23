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

    /**
     * Inserts per JDBC batch. Flush and clear the EntityManager after so many inserts.
     */
    public static final int JDBC_BATCH_SIZE = 20;

    public DataConnectionTraversal fillHibernateProperties(final DataConnectionTraversal dataConnectionTraversal) {
        final Map<String, Object> hibernateProperties = new HashMap<>();
        // The Hikari pool of DataSourceFactoryService. Hibernate does not create a pool of its own, therefore no
        // hibernate.hikari.* properties: the pool is configured in DataSourceFactoryService.
        hibernateProperties.put("jakarta.persistence.nonJtaDataSource", dataConnectionTraversal.dataSource());
        // No hibernate.dialect: Hibernate detects H2 from the connection (HHH90000025 otherwise)
        // Liquibase creates the schema, Hibernate only checks that the entities match it
        hibernateProperties.put("hibernate.hbm2ddl.auto", "validate"); // validate, update, create, create-drop
        hibernateProperties.put("hibernate.show_sql", "true");
        hibernateProperties.put("hibernate.format_sql", "true");
        hibernateProperties.put("hibernate.use_sql_comments", "true");
        hibernateProperties.put("hibernate.jdbc.batch_size", String.valueOf(JDBC_BATCH_SIZE));
        hibernateProperties.put("hibernate.order_inserts", "true");
        hibernateProperties.put("hibernate.order_updates", "true");
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

    /**
     * A new EntityManager: the caller closes it.
     */
    public DataConnectionTraversal fillEntityManager(final DataConnectionTraversal dataConnectionTraversal) {
        return dataConnectionTraversal.toBuilder()
            .entityManager(dataConnectionTraversal.entityManagerFactory().createEntityManager())
            .build();
    }

    public void close(final DataConnectionTraversal dataConnectionTraversal) {
        if (dataConnectionTraversal.entityManagerFactory() != null) {
            dataConnectionTraversal.entityManagerFactory().close();
        }
    }
}
