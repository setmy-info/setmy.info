package ee.pub.service;

import ee.pub.dao.IPersonDao;
import ee.pub.dao.PersonRepository;
import ee.pub.model.Person;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;

/**
 * PersonDao class.
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Named("personService")
@Transactional
public class PersonService {

    private static final Logger LOG = Logger.getLogger(PersonService.class);

    @Inject
    private IPersonDao personDao;

    @Inject
    private PersonRepository personRepository;

    public Person save(final Person person) {
        return personRepository.save(person);//return personDao.save(person);
    }

    public Person find(final Long id) {
        return personRepository.findOne(id);//return personDao.find(id);
    }

    public List<Person> findAll() {
        return personDao.findAll();
        //return personRepository.findAll();
    }

    public Page<Person> findByFirstName(final String firstName) {
        //return personRepository.findByFirstName(firstName);
        final Pageable page = new PageRequest(
                0, 20, Direction.ASC, "firstName"//Sort by firstName
        );
        final Page<Person> results = personRepository.findByFirstName(firstName, page);
        return results;//personRepository.findByFirstName(firstName);
    }

    public Person update(final Person person) {
        return personRepository.save(person);// return personDao.update(person);
    }

    public void delete(final Long id) {
        personRepository.delete(id);//personDao.delete(id);
    }
}
