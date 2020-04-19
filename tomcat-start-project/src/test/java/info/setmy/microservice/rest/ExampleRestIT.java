package info.setmy.microservice.rest;

import static io.restassured.RestAssured.when;
import io.restassured.parsing.Parser;
import static org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class ExampleRestIT {

    private final String URL = "http://localhost:8777/tomcat-start-project-1.2.0-SNAPSHOT/rest/hello";

    @Test
    public void testHello() {
        when().get(URL).then().parser("text/plain", Parser.TEXT).contentType("text/plain").statusCode(200).body(containsString("Hello World from DB"));
    }
}
