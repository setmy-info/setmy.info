package info.setmy.microservice.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;

/**
 * JPA standard base DAO class.
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class JPABaseDao {

    public static final String PERSISTENCE_UNIT_NAME = "default";

    @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME, type = PersistenceContextType.TRANSACTION)
    protected EntityManager entityManager;
}
