package info.setmy.microservice.dal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;

public class JPABaseDao {

    public static final String PERSISTENCE_UNIT_NAME = "default";

    @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME, type = PersistenceContextType.TRANSACTION)
    protected EntityManager entityManager;
}
