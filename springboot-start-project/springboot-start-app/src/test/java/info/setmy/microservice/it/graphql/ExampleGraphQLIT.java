package info.setmy.microservice.it.graphql;

import info.setmy.microservice.it.RestBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

@Slf4j
class ExampleGraphQLIT extends RestBase {

    //https://devqa.io/rest-assured-api-requests-examples/
    @Test
    void testFindAll() {
        log.info("testFindAll");
        getRestRequest()
            .and()
            .body("{\"query\":\"{bookById(id: \\\"book-1\\\") {id name pageCount author {id firstName lastName}}}\",\"variables\":{},\"operationName\":null}")
            .post(getGraphQLUrl())
            .then()
            .assertThat()
            //.contentType("application/json")
            .statusCode(200)
            .header("Connection", "keep-alive")
            //.header("Content-Encoding", "gzip") ???
            .header("Date", not(isEmptyOrNullString()))
            .body("size()", is(1))
            .body("data.bookById.name", equalTo("Harry Potter and the Philosopher's Stone"));
    }
}
