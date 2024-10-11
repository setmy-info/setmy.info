package info.setmy.microservice.it.rest;

import info.setmy.microservice.it.RestBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

@Slf4j
public class AnotherExampleRestIT extends RestBase {

    private final static String RESOURCE_URL = "/anotherExample";

    @Test
    @Disabled
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
            .body("size()", is(2))
            .body("exampleString", equalTo("Hello World from DB"));
    }
}
