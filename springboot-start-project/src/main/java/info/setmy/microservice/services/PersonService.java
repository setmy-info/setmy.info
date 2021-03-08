package info.setmy.microservice.services;

import info.setmy.microservice.components.PersonTransactionErrorComponent;
import info.setmy.microservice.dao.PersonRepository;
import info.setmy.microservice.example.model.Person;
import java.util.List;
import javax.inject.Named;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static javax.transaction.Transactional.TxType.MANDATORY;
import static javax.transaction.Transactional.TxType.REQUIRES_NEW;

/**
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Named("personService")
//@Transactional
public class PersonService {

    private final Logger log = LogManager.getLogger(getClass());

    private final PersonRepository personRepository;

    private final PersonTransactionErrorComponent personTransactionErrorComponent;

    public PersonService(final PersonRepository personRepository, final PersonTransactionErrorComponent personTransactionErrorComponent) {
        this.personRepository = personRepository;
        this.personTransactionErrorComponent = personTransactionErrorComponent;
    }

    private long index = 0;

    @Transactional
    public void getTransaction() {
        getTransactionNonFailing();
        getTransactionNewFailing();
    }

    @Transactional(MANDATORY)
    public void getTransactionNonFailing() {
        personRepository.save(newPerson());
    }

    @Transactional(REQUIRES_NEW)
    public void getTransactionNewFailing() {
        personRepository.save(newPerson());
        personTransactionErrorComponent.errorOnDemand();
    }

    @Transactional
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    private Person newPerson() {
        index++;
        final Person person = new Person();
        person.setId(index);
        person.setFirstName("First name");
        person.setLastName("Last name");
        log.info("Peron: {}", person);
        return person;
    }

}
