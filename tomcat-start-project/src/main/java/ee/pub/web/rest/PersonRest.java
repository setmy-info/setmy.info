package ee.pub.web.rest;

import ee.pub.model.Person;
import ee.pub.service.DozerService;
import ee.pub.service.PersonService;
import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
/**
 * 
 * 
 */
@Named
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonRest {

    private static final Logger LOG = Logger.getLogger(PersonRest.class);

    public static final String URL = "/person";

    @Inject
    private PersonService personService;

    @Inject
    private DozerService dozerService;

    @POST
    @Path(URL)
    public Person save(final Person person) {// Create
        return dozerService.personToPerson(personService.save(person));
    }

    @DELETE
    @Path(URL + "/{id}")
    public void delete(@PathParam("id") final Long id) {// Delete
        personService.delete(id);
    }

    @PUT
    @Path(URL)
    public Person update(final Person person) {// Update
        return dozerService.personToPerson(personService.update(person));
    }

    @GET
    @Path(URL + "/{id}") // Read
    public Person find(@PathParam("id") final Long id) throws ParseException {
        return dozerService.personToPerson(personService.find(id));
    }

    @GET
    @Path(URL)
    public List<Person> findAll() throws ParseException {// Read
        return personService.findAll();
    }

    @GET
    @Path(URL + "/firstName/{firstName}")
    public Page<Person> findByFirstName(@PathParam("firstName") final String firstName) throws ParseException {// Read
        return personService.findByFirstName(firstName);
    }
}
