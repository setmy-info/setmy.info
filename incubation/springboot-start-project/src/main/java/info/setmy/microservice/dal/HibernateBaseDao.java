package info.setmy.microservice.dal;

import jakarta.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Getter
@Setter
public class HibernateBaseDao {

    @Inject
    private SessionFactory sessionFactory;

    protected Session getSession() {
        final Session session = sessionFactory.openSession();
        return session;
    }
}
