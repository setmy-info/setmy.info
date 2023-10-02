package info.setmy.microservice.services;

import info.setmy.microservice.RESTIntegrationTestBase;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class RESTExampleControllerIT extends RESTIntegrationTestBase {

    @Override
    public String getResource() {
        return "/api/example";
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
