package ee.pub.web.rest;

import ee.pub.model.Person;
import ee.pub.service.CourseService;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Accept : application/json
 * http://localhost:8080/tomcat-start-project/rest/public/example/person/first/Imre/last/Tabur
 *
 * Accept : application/json Content-Type : application/json
 * http://localhost:8080/tomcat-start-project/rest/public/example/echo
 * {"firstName":"Imre","lastName":"Tabur"}
 *
 * Content-Type : application/json
 * http://localhost:8080/tomcat-start-project/rest/public/example/graph/add
 * {"firstName":"Imre","lastName":"Tabur"}
 *
 * http://localhost:8080/tomcat-start-project/rest/public/example/graph/get/1
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Named
@Path("/example")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExampleRest {

    @Inject
    private CourseService service;

    @GET
    @Path("/person/first/{firstName}/last/{lastName}")
    public Person getTrackInJSON(@PathParam("firstName") final String firstName, @PathParam("lastName") final String lastName) {
        Person ret = service.makePerson(firstName, lastName);
        return ret;
    }

    @POST
    @Path("/echo")
    public Response createTrackInJSON(Person person) {
        return Response.status(201).entity(person).build();
    }

    @POST
    @Path("/graph/add")
    public Response addGraphPerson(Person person) {
        person = this.service.addPerson(person);
        return Response.status(201).entity(person).build();
    }

    @GET
    @Path("/graph/get/{id}")
    public Response getGraphPerson(@PathParam("id") final Long id) {
        Person ret = service.getPerson(id);
        if (ret != null) {
            return Response.status(201).entity(ret).build();
        }
        return Response.status(200).entity("{}").build();
    }

    @GET
    @Path("/graph/getall")
    public Response getGraphAllPerson() {
        List<Person> ret = service.getAllPersons();
        if (!ret.isEmpty()) {
            return Response.status(201).entity(ret).build();
        }
        return Response.status(200).entity("{}").build();
    }
}
