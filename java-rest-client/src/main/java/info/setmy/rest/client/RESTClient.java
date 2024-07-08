package info.setmy.rest.client;

import info.setmy.rest.client.model.AuthenticationCredentials;
import info.setmy.rest.client.model.Proxy;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Feature;
import lombok.RequiredArgsConstructor;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.logging.LoggingFeature;

import java.util.Optional;
import java.util.logging.Logger;

import static java.util.Optional.ofNullable;
import static java.util.logging.Level.INFO;
import static org.glassfish.jersey.logging.LoggingFeature.DEFAULT_LOGGER_NAME;
import static org.glassfish.jersey.logging.LoggingFeature.Verbosity.PAYLOAD_ANY;

/**
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@RequiredArgsConstructor
public class RESTClient {

    private final String baseUri;
    private final Proxy proxy;
    private final AuthenticationCredentials credentials;
    private final RESTClientConfig clientConfig;

    @Deprecated
    public RESTClient(
        final String baseUri) {
        this.baseUri = baseUri;
        this.proxy = null;
        this.credentials = null;
        this.clientConfig = null;
    }

    @Deprecated
    public RESTClient(
        final String baseUri,
        final Proxy proxy,
        final AuthenticationCredentials credentials) {
        this.baseUri = baseUri;
        this.proxy = proxy;
        this.credentials = credentials;
        this.clientConfig = null;
    }

    public Invocation.Builder getBuilder(final String path) {
        final WebTarget target = newTarget(path);
        final Invocation.Builder builder = target.request();
        return addHeaders(addCookies(addProperties(builder)));
    }

    private Invocation.Builder addProperties(final Invocation.Builder builder) {
        getClientConfig().ifPresent(restClientConfig -> restClientConfig.getDefaultProperties().forEach((key, value) -> builder.property(key, value)));
        return builder;
    }

    private Invocation.Builder addCookies(final Invocation.Builder builder) {
        getClientConfig().ifPresent(restClientConfig -> restClientConfig.getDefaultCookies().forEach((key, value) -> builder.header(key, value)));
        return builder;
    }

    private Invocation.Builder addHeaders(final Invocation.Builder builder) {
        getClientConfig().ifPresent(restClientConfig -> restClientConfig.getDefaultHeaders().forEach((key, value) -> builder.header(key, value)));
        return builder;
    }

    private Optional<RESTClientConfig> getClientConfig() {
        return ofNullable(clientConfig);
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
        getClientConfig().ifPresent(restClientConfig -> {
            if (restClientConfig.isDebugLogging()) {
                clientConfig.register(new LoggingFeature(Logger.getLogger(DEFAULT_LOGGER_NAME), INFO, PAYLOAD_ANY, 10000));
            }
        });
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
