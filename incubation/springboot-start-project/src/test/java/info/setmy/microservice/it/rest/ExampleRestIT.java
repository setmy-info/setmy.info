package info.setmy.microservice.it.rest;

import info.setmy.microservice.it.RestBase;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class ExampleRestIT extends RestBase {

    private final static String RESOURCE_URL = "/example";

    @Test
    public void testFindAll() {
        getRestRequest()
            .get(getRestUrl(RESOURCE_URL))
            .then()
            .assertThat()
            .contentType("application/json")
            .statusCode(200)
            .header("Connection", "keep-alive")
            //.header("Content-Encoding", "gzip") ???
            .header("Date", not(isEmptyOrNullString()))
            .body("size()", is(1))
            .body("exampleString", equalTo("Hello World from DB"));
    }
}
