package info.setmy.microservice.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 * JPA standard base DAO class.
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class JPABaseDao {

    public static final String PERSISTENCE_UNIT_NAME = "default";

    @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME, type = PersistenceContextType.TRANSACTION)
    protected EntityManager entityManager;
}
