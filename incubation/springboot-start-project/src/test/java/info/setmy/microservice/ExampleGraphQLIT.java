package info.setmy.microservice;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

public class ExampleGraphQLIT extends RestBase {

    @Override
    public String getResource() {
        return "/graphql";
    }

    @Test
    public void testFindAll() {
        /*
        getRequest().get(getUrl(""))
            .then()
            .assertThat()
            .contentType("application/json")
            .statusCode(200)
            .header("Connection", "keep-alive")
            //.header("Content-Encoding", "gzip") ???
            .header("Date", not(isEmptyOrNullString()))
            .body("size()", is(1))
            .body("exampleString", equalTo("Hello World from DB"));
        */
    }
}
