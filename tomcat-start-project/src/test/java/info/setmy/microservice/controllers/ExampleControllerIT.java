package info.setmy.microservice.controllers;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class ExampleControllerIT {

    private final String URL = "http://localhost:8777/tomcat-start-project-1.2.0-SNAPSHOT/api/example";

    @Test
    public void testHello() {
        when().get(URL).then().statusCode(200).body("text", equalTo("Hello World from DB"));
    }
}
