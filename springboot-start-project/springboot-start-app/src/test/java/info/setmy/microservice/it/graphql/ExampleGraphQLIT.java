package info.setmy.microservice.it.graphql;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import info.setmy.microservice.it.WebSpringBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.util.Map;

import static info.setmy.microservice.web.constant.ApiConstants.GRAPHQL_BASE;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class ExampleGraphQLIT extends WebSpringBase {

    private static final String GRAPHQL_URL = "http://localhost:8777" + GRAPHQL_BASE;

    private static final String QUERY = "{\"query\":\"{bookById(id: \\\"book-1\\\") {id name pageCount author {id firstName lastName}}}\",\"variables\":{},\"operationName\":null}";

    //https://devqa.io/rest-assured-api-requests-examples/
    @Test
    void testFindAll() {
        log.info("testFindAll");
        final ResponseEntity<String> response = RestClient.create()
            .post()
            .uri(GRAPHQL_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .body(QUERY)
            .retrieve()
            .toEntity(String.class);

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getHeaders().getFirst("Date")).isNotBlank();

        final DocumentContext json = JsonPath.parse(response.getBody());
        final Map<String, Object> root = json.read("$");
        assertThat(root).hasSize(1);
        assertThat(json.<String>read("$.data.bookById.name"))
            .isEqualTo("Harry Potter and the Philosopher's Stone");
    }
}
