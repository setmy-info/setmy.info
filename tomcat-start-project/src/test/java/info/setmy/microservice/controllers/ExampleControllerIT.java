package info.setmy.microservice.controllers;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class ExampleControllerIT {

    private final String URL = "http://localhost:8777/tomcat-start-project-1.2.0-SNAPSHOT/api/example";

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testHello() {
        when().get(URL).then().statusCode(200).body("text", equalTo("Hello World from DB"));
    }
}
