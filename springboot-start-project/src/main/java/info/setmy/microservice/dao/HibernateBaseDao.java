package info.setmy.microservice.dao;

import javax.inject.Inject;
import org.hibernate.SessionFactory;

/**
 * Hibernate specific DAO class.
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class HibernateBaseDao {

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Inject
    private SessionFactory sessionFactory;
}
