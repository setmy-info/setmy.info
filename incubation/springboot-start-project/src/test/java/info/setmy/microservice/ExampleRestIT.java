package info.setmy.microservice;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

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
