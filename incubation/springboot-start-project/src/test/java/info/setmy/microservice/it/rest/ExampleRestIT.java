package info.setmy.microservice.it.rest;

import info.setmy.microservice.it.RestBase;
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

    @Test
    public void testRollback() {
        getRestRequest()
            .body("{\"exampleString\": \"Hello World!\"}")
            .post(getRestUrl(RESOURCE_URL + "/rollback"))
            .then()
            .assertThat()
            .contentType("application/json")
            .statusCode(500)
            .header("Connection", "close")
            //.header("Content-Encoding", "gzip") ???
            .header("Date", not(isEmptyOrNullString()))
            .body("size()", is(4))
            //{"timestamp":"2024-05-20T05:09:54.993+00:00","status":999,"error":"None","key":"key.for.some.error"}
            .body("key", equalTo("key.for.some.error"))
            .body("error", equalTo("None"))
            .body("status", equalTo(999));
    }
}
