package info.setmy.rest.client.model;

import info.setmy.rest.client.RESTClient;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class RESTClientIT {

    RESTClient client;

    @Test
    //@Ignore
    public void before() {
        final AuthenticationCredentials credentials = new AuthenticationCredentials();
        credentials.setUserName("user");
        credentials.setPassword("passw");
        client = new RESTClient("http://localhost:8777", null, credentials);
        final Invocation.Builder builder = client.getBuilder("example.json").
                accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML).
                header(HttpHeaders.AUTHORIZATION, "jwtstring.ss.ss");
        final ExampleModel result = builder.get(ExampleModel.class);
        assertThat(result.getText(), is(equalTo("Hello World")));
    }

}
