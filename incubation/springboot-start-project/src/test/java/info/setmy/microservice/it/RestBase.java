package info.setmy.microservice.it;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import static info.setmy.microservice.web.constant.ApiConstants.API_BASE;
import static info.setmy.microservice.web.constant.ApiConstants.GRAPHQL_BASE;
import static io.restassured.RestAssured.given;

@Getter
@Log4j2
public abstract class RestBase extends WebSpringBase {

    private static final String REST_URL = "http://localhost:8777" + API_BASE;

    private static final String GRAPHQL_URL = "http://localhost:8777" + GRAPHQL_BASE;

    private static final String HTML_URL = "http://localhost:8777";

    public String getRestUrl(final String resourceUrl) {
        return new StringBuilder(REST_URL.length() + resourceUrl.length() + 1)
            .append(REST_URL)
            .append(resourceUrl)
            .toString();
    }

    public String getGraphQLUrl() {
        return GRAPHQL_URL;
    }

    public String getHTMLUrl(final String resourceUrl) {
        return new StringBuilder(HTML_URL.length() + resourceUrl.length() + 1)
            .append(HTML_URL)
            .append(resourceUrl)
            .toString();
    }

    public RequestSpecification getRestRequest() {
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
