package info.setmy.crawler.dal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class HibernateComponent {

    private final Map<String, Object> hibernateProperties;
    private final DataSource dataSource;
    private EntityManagerFactory entityManagerFactory;

    public HibernateComponent init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("appPU", hibernateProperties);
        return this;
    }

    public EntityManager createEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
