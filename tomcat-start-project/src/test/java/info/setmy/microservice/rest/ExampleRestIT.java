package info.setmy.microservice.rest;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.withNoArgs;
import io.restassured.parsing.Parser;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class ExampleRestIT {

    private final String URL = "http://localhost:8777/tomcat-start-project-1.2.0-SNAPSHOT/rest/hello";

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testHello() {
        when().get(URL).then().parser("text/plain", Parser.TEXT).contentType("text/plain").statusCode(200).body(containsString("Hello World from DB"));
    }
}
