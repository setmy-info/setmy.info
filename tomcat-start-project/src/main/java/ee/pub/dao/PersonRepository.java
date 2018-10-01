package ee.pub.dao;

import ee.pub.model.Person;
import java.util.List;
import javax.inject.Named;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PersonRepository class.
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Named("personRepository")
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByFirstName(final String firstName);

    Page<Person> findByFirstName(final String firstName, final Pageable pageable);
}
