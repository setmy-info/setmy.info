package info.setmy.microservice.services;

import info.setmy.microservice.RESTIntegrationTestBase;
import static info.setmy.microservice.controllers.ExampleController.EXAMPLE_REST_RESOURCE;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class RESTExampleRestIT extends RESTIntegrationTestBase {

    @Override
    public String getResource() {
        return EXAMPLE_REST_RESOURCE;
    }

    @Test
    public void testFindAll() {
        getRequest().get(getUrl(""))
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()", is(3))
                // .body("[0].text", equalTo("xxx")); // For arrays
                .body("text", equalTo("Hello World from DB"));
    }
}
