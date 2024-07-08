package info.setmy.rest.client.model;

import info.setmy.rest.client.RESTClient;
import info.setmy.rest.client.RESTClientConfig;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class RESTClientIT {

    RESTClient client;

    @Test
    //@Ignore
    public void before() {
        final AuthenticationCredentials credentials = new AuthenticationCredentials();
        final RESTClientConfig clientConfig = RESTClientConfig.builder()
            .debugLogging(true)
            .build()
            .applyDefaultConfig();
        credentials.setUserName("user");
        credentials.setPassword("passw");
        // -Dsun.net.http.allowRestrictedHeaders=true
        client = new RESTClient("http://localhost:8777", null, credentials, clientConfig);
        final Invocation.Builder builder = client.getBuilder("example.json").
            accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML).
            header(HttpHeaders.AUTHORIZATION, "jwtstring.ss.ss");
        final ExampleModel result = builder.get(ExampleModel.class);
        assertThat(result.getText()).isEqualTo("Hello World");
    }

}
