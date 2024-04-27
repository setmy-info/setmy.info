package info.setmy.microservice.it.graphql;

import info.setmy.microservice.it.RestBase;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

public class ExampleGraphQLIT extends RestBase {

    //https://devqa.io/rest-assured-api-requests-examples/
    @Test
    public void testFindAll() {
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
