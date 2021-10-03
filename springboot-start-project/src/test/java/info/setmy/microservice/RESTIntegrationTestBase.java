package info.setmy.microservice;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public abstract class RESTIntegrationTestBase extends SpringIntegrationTestBase {

    final Logger log = LogManager.getLogger(this.getClass());

    protected static final String URL = "http://localhost:8080";

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
