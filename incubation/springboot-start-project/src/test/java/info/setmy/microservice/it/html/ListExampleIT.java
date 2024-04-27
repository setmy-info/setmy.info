package info.setmy.microservice.it.html;

import info.setmy.microservice.it.RestBase;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

public class ListExampleIT extends RestBase {

    private final static String RESOURCE_URL = "/listExample";

    @Test
    public void testFindAll() {
        getRestRequest()
            .get(getHTMLUrl(RESOURCE_URL))
            .then()
            .assertThat()
            .contentType("text/html;charset=UTF-8")
            .statusCode(200)
            .header("Connection", "keep-alive")
            //.header("Content-Encoding", "gzip") ???
            .header("Date", not(isEmptyOrNullString()))
            .body("html.head.title", equalTo("List Example"))
            .body("html.body.h1", equalTo("List Example"))
            .body("html.body.ul.li[0]", equalTo("a"))
            .body("html.body.ul.li[1]", equalTo("b"))
            .body("html.body.ul.li[2]", equalTo("c"));
    }
}
