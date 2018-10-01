package ee.pub.dao;

import javax.inject.Inject;
import org.hibernate.SessionFactory;

/**
 * HibernateBaseDao class.
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class HibernateBaseDao {

    @Inject
    private SessionFactory sessionFactory;
}
