package info.setmy.microservice.universum.rest;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class UniversumRestIT {

    private final String URL = "http://localhost:8080/tomcat-start-project-1.2.0-SNAPSHOT/rest/universum";

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testHello() {
        when().get(URL).then().statusCode(200).body("", equalTo("Sun"));
    }
}
