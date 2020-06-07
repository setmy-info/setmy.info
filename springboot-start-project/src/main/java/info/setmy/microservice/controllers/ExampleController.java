package info.setmy.microservice.controllers;

import static info.setmy.microservice.constances.RESTConstantces.API_RESOURCE_PATH;
import info.setmy.microservice.models.ExampleModel;
import info.setmy.microservice.services.ExampleService;
import javax.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST call: http://localhost:8080/api/example
 *
 * JSON headers needed.
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Named("exampleController")
@RestController
@RequestMapping(
        path = ExampleController.EXAMPLE_REST_RESOURCE,
        produces = APPLICATION_JSON_VALUE,
        consumes = APPLICATION_JSON_VALUE
)
public class ExampleController {

    final Logger log = LogManager.getLogger(getClass());

    public static final String EXAMPLE_REST_RESOURCE = API_RESOURCE_PATH + "/example";

    private final ExampleService exampleService;

    public ExampleController(final ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @GetMapping(value = "")
    public ExampleModel example() {
        log.info("Spring controller called!!");
        return exampleService.getExampleModel();
    }
}
