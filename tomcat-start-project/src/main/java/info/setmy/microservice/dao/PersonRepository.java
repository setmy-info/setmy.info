package info.setmy.microservice.dao;

import info.setmy.microservice.example.model.Person;
import javax.inject.Named;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Named("personRepository")
public interface PersonRepository extends JpaRepository<Person, Long> {
}
