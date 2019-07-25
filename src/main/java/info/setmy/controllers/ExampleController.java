package info.setmy.controllers;

import info.setmy.models.ExampleModel;
import info.setmy.properties.ExampleProperties;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * REST call: http://localhost:8080/api/example .
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Named("exampleController")
@Controller("/rest")
public class ExampleController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final ExampleProperties exampleProperties;

    public ExampleController(final ExampleProperties exampleProperties) {
        this.exampleProperties = exampleProperties;
    }

    @Get("/example")
    @Produces(MediaType.APPLICATION_JSON)
    public ExampleModel example() {
        log.info("Spring controller called!!");
        final ExampleModel exampleModel = new ExampleModel();
        exampleModel.setText("Hello world!");
        exampleModel.setExampleProperties(exampleProperties);
        log.info("Returning: {}", exampleModel);
        return exampleModel;
    }
}
