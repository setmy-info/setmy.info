package ee.pub.dao;

import ee.pub.model.Person;
import java.util.List;

/**
 * PersonDao class.
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public interface IPersonDao {

    Person save(final Person todo);

    Person find(final Long id);

    List<Person> findAll();

    Person update(final Person todo);

    void delete(final Long id);
}
