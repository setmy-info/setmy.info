package ee.pub.hibernate.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Dao<T, ID extends Serializable> {

    protected final Class<T> persistentClass;
    private SessionFactory sessionFactory;

    public Dao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public T save(final T object) {
        this.getSession().saveOrUpdate(object);
        return object;
    }

    public T find(ID id) {
        return (T) this.getSession().load(persistentClass, id);
    }
}
