package info.setmy.microservice;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;

import static info.setmy.microservice.ApiConstants.API_BASE;
import static info.setmy.microservice.ApiConstants.GRAPHQL_BASE;
import static io.restassured.RestAssured.given;

@Log4j2
public abstract class RestBase extends WebSpringBase {

    protected static final String URL = "http://localhost:8777" + API_BASE;
    protected static final String GRAPHQL_URL = "http://localhost:8777" + GRAPHQL_BASE;

    protected String getUrl() {
        return URL + getResource();
    }

    protected String getGraphQLUrl() {
        return GRAPHQL_URL;
    }

    public abstract String getResource();

    protected String getUrl(final String path) {
        final String requestUrl = getUrl() + path;
        log.info("Requesting URL: '{}'", requestUrl);
        return getUrl() + path;
    }

    protected RequestSpecification getRequest() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        final RequestSpecification requestSpecification = given()
            // Taken from RESTClient headers
            .header("Accept-Encoding", "gzip,deflate,br")
            .header("Accept-Language", "en-US,en;q=0.5")
            .header("Connection", "keep-alive")
            .header("Content-Type", "application/json")
            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:124.0) Gecko/20100101 Firefox/124.0")
            .header("Accept", "application/json");
        return requestSpecification;
    }
}
