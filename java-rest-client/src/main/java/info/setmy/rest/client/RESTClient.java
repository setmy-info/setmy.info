package info.setmy.rest.client;

import info.setmy.rest.client.model.AuthenticationCredentials;
import info.setmy.rest.client.model.Proxy;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class RESTClient {

    private final String baseUri;
    private final Proxy proxy;
    private final AuthenticationCredentials credentials;

    public RESTClient(
            final String baseUri) {
        this.baseUri = baseUri;
        this.proxy = null;
        this.credentials = null;
    }

    public RESTClient(
            final String baseUri,
            final Proxy proxy,
            final AuthenticationCredentials credentials) {
        this.baseUri = baseUri;
        this.proxy = proxy;
        this.credentials = credentials;
    }

    public Invocation.Builder getBuilder(final String path) {
        final WebTarget target = newTarget(path);
        final Invocation.Builder builder = target.request();
        return builder;
    }

    private WebTarget newTarget(final String path) {
        final WebTarget baseTarget = newBaseTarget();
        final WebTarget target = baseTarget.path(path);
        return target;
    }

    private WebTarget newBaseTarget() {
        final Client client = newClient();
        final WebTarget baseTarget = client.target(baseUri);
        return baseTarget;
    }

    private Client newClient() {
        final ClientConfig clientConfig = newClientConfig();
        final Client client = ClientBuilder.newClient(clientConfig);
        return client;
    }

    private ClientConfig newClientConfig() {
        final ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(getFeature());
        return clientConfig;
    }

    Feature getFeature() {
        final HttpAuthenticationFeature.BasicBuilder builder = HttpAuthenticationFeature.basicBuilder()
                .nonPreemptive();
        if (credentials != null) {
            builder.credentials(credentials.getUserName(), credentials.getPassword());// X-Auth-xx??
        }
        return builder.build();
    }
}
