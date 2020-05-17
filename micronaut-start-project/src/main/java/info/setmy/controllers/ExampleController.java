package info.setmy.controllers;

import static info.setmy.constants.CacheConstants.CACHE_NAME;
import info.setmy.models.ExampleModel;
import info.setmy.properties.ExampleProperties;
//import io.micronaut.cache.annotation.Cacheable;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import javax.inject.Named;
import org.infinispan.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * REST call: http://localhost:8080/rest/example .
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Named("exampleController")
@Controller("/rest")
public class ExampleController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final String EXAMPLE_MODEL = "exampleModel";

    private final ExampleProperties exampleProperties;

    private final Cache cache;

    public ExampleController(final ExampleProperties exampleProperties,
            final Cache cache) {
        this.exampleProperties = exampleProperties;
        this.cache = cache;
    }

    @Get("/example")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //@Cacheable(CACHE_NAME)
    public ExampleModel example() {
        log.info("Micronaut controller called!!");
        final ExampleModel exampleModel = new ExampleModel();
        exampleModel.setText("Hello world!");
        exampleModel.setExampleProperties(exampleProperties);
        log.info("Returning: {}", exampleModel);

        cache.put("exampleModel", exampleModel);
        cache.get(EXAMPLE_MODEL);

        return (ExampleModel) cache.get(EXAMPLE_MODEL);
    }
}
