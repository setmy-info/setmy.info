package info.setmy.microservice.controllers;

import info.setmy.microservice.example.model.Person;
import info.setmy.microservice.services.PersonService;
import java.util.List;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static info.setmy.microservice.constances.RESTConstantces.API_RESOURCE_PATH;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * REST call: http://localhost:8080/api/person
 * <p>
 * JSON headers needed.
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Named("personController")
@RestController
@RequestMapping(
    path = PersonController.PERSON_REST_RESOURCE,
    produces = APPLICATION_JSON_VALUE,
    consumes = APPLICATION_JSON_VALUE
)
public class PersonController {

    private final Logger log = LogManager.getLogger(getClass());

    public static final String PERSON_REST_RESOURCE = API_RESOURCE_PATH + "/person";

    private final PersonService personService;

    public PersonController(final PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "")
    public List<Person> person() {
        log.info("Spring controller called!!");
        personService.getTransaction();
        return personService.getAll();
    }
}
