package info.setmy.microservice.it.rest;

import info.setmy.microservice.it.RestBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

@Slf4j
class AnotherExampleRestIT extends RestBase {

    private final static String RESOURCE_URL = "/anotherExample";

    @Test
    public void testFindAll() {
        log.info("testFindAll");
        getRestRequest()
            .get(getRestUrl(RESOURCE_URL))
            .then()
            .assertThat()
            .contentType("application/json")
            .statusCode(200)
            .header("Connection", "keep-alive")
            //.header("Content-Encoding", "gzip") ???
            .header("Date", not(isEmptyOrNullString()))
            .body("size()", is(3))
            .body("exampleString", equalTo("Hello World from DB"))
            .body("geom", equalTo("POLYGON ((30 10, 40 40, 20 40, 10 20, 30 10))"));
    }
}
