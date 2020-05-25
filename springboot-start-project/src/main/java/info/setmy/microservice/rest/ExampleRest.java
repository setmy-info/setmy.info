package info.setmy.microservice.rest;

import info.setmy.microservice.services.ExampleService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * Example Jersey rest interface (resource).
 *
 * REST call: http://localhost:8080/rest/hello
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Component("exampleRest")//@Named("exampleRest") - is not working with AOP
@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExampleRest {

    final Logger log = LogManager.getLogger(getClass());

    private final ExampleService exampleService;

    public ExampleRest(final ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @GET
    @Produces("text/plain")
    @Cacheable("apiCache")
    public String hello() {
        log.info("Jersey Hello REST called!!");
        return exampleService.getExampleModel().getText();
    }
}
