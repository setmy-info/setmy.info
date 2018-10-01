package ee.pub.dao;

import ee.pub.model.Person;
import java.util.List;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;

/**
 * JPAPersonDao class.
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Named("personDao")
public class JPAPersonDao extends JPABaseDao implements IPersonDao {

    private static final Logger LOG = Logger.getLogger(JPAPersonDao.class);

    public static final String FIND_ALL = "findAll";

    public static final String FIND_ALL_QUERY = "SELECT person FROM Person person ORDER BY person.id ASC";

    public static final String FIND = "find";

    public static final String ID = "id";

    public static final String FIND_QUERY = "SELECT person FROM Person person WHERE person.id = :" + ID;

    @Override
    public Person save(final Person person) {
        LOG.debug(String.format("Saving person firstName=%s, lastName=%s",
                person.getFirstName(), person.getLastName()));
        entityManager.persist(person);
        entityManager.flush();
        return person;
    }

    @Override
    public Person find(final Long id) {
        LOG.debug(String.format("Searching person by id=%d", id));
        final TypedQuery<Person> query = entityManager.createNamedQuery(FIND, Person.class);
        query.setParameter(ID, id);
        return query.getSingleResult();
    }

    @Override
    public List<Person> findAll() {
        LOG.debug("Finding all persons");
        final TypedQuery<Person> query = entityManager.createNamedQuery(FIND_ALL, Person.class);
        return query.getResultList();
    }

    @Override
    public Person update(final Person person) {
        LOG.debug(String.format(
                "Updating id=%d, firstName=%s, lastName=%s",
                person.getId(), person.getFirstName(), person.getLastName()));
        final Person personToUpate = find(person.getId());
        personToUpate.setFirstName(person.getFirstName());
        personToUpate.setLastName(person.getLastName());
        entityManager.persist(personToUpate);
        entityManager.flush();
        return personToUpate;
    }

    @Override
    public void delete(final Long id) {
        LOG.debug("Deleting person by ID=" + id);
        final Person person = find(id);
        entityManager.remove(person);
    }
}
