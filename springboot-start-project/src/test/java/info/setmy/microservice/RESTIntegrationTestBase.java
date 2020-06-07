package info.setmy.microservice;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Log4j2
public abstract class RESTIntegrationTestBase extends SpringIntegrationTestBase {

    protected static final String URL = "http://robot.lan:8080";

    public abstract String getResource();

    protected String getUrl(final String path) {
        final String requestUrl = getUrl() + path;
        log.info("Requesting URL: '{}'", requestUrl);
        return getUrl() + path;
    }

    protected String getUrl() {
        return URL + getResource();
    }

    protected RequestSpecification getRequest() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        final RequestSpecification requestSpecification = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");
        return requestSpecification;
    }
}
