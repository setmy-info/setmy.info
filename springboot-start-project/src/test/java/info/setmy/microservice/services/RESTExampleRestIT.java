package info.setmy.microservice.services;

import info.setmy.microservice.RESTBase;
import static info.setmy.microservice.controllers.ExampleController.EXAMPLE_REST_RESOURCE;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RESTExampleRestIT extends RESTBase {

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
