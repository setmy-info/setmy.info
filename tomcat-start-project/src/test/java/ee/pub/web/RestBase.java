package ee.pub.web;

import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.response.ValidatableResponse;
import com.jayway.restassured.specification.RequestSpecification;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Imre Tabur <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class RestBase {

    protected static final String BASE_URL = "http://localhost:8080/tomcat-start-project";
    protected static final int PORT = 8080;
    protected RequestSpecification given;
    protected RequestSpecification when;
    protected ValidatableResponse then;

    protected RequestSpecification getGiven(final String url) {
        RestAssured.basePath = url;
        this.given = given().contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        final RequestSpecification resultGiven = given().contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        when = resultGiven.when();
        return resultGiven;
    }
}
