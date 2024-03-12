package info.setmy.microservice;

import jakarta.inject.Inject;
import org.hibernate.SessionFactory;

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
