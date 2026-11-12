package info.setmy.microservice.it.security;

import info.setmy.microservice.it.WebSpringBase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Developer tools must stay open without a token: real HTTP calls to the started server, not MockMvc.
 * H2 console is not here: it is enabled only in the local profile (application-local.yml).
 */
class PublicEndpointsIT extends WebSpringBase {

    private static final String BASE_URL = "http://localhost:8777";

    private final HttpClient httpClient = HttpClient.newBuilder()
        .followRedirects(HttpClient.Redirect.NORMAL)
        .build();

    @ParameterizedTest
    @ValueSource(strings = {
        "/swagger-ui/index.html",
        "/swagger-ui.html",
        "/v3/api-docs",
        "/v3/api-docs.yaml",
        "/actuator",
        "/actuator/health",
        "/actuator/info",
        "/api/public/auth-config"
    })
    void openWithoutToken(final String path) throws IOException, InterruptedException {
        final HttpResponse<String> response = httpClient.send(
            HttpRequest.newBuilder(URI.create(BASE_URL + path)).GET().build(),
            HttpResponse.BodyHandlers.ofString()
        );
        assertThat(response.statusCode()).as("GET %s, body: %s", path, response.body()).isEqualTo(200);
    }
}
