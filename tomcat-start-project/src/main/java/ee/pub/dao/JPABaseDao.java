package ee.pub.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 * JPABaseDao class.
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public abstract class JPABaseDao {

    public static final String PERSISTENCE_UNIT_NAME = "projectPersistence";

    @PersistenceContext(unitName = PERSISTENCE_UNIT_NAME, type = PersistenceContextType.TRANSACTION)
    protected EntityManager entityManager;
}
