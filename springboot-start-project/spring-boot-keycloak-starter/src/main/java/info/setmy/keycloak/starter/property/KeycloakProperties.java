package info.setmy.keycloak.starter.property;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Keycloak (OIDC provider) settings.
 * <p>
 * application.yaml maps the values from SMI_IAM_* placeholders, for example
 * {@code url: ${SMI_IAM_URL}}. An environment variable with the same name
 * overrides the default given in application.yaml. See keycloak.md for the
 * list.
 * <p>
 * Registered by {@link info.setmy.keycloak.starter.KeycloakAutoConfiguration}.
 */
@Getter
@Setter
@ToString
@ConfigurationProperties(KeycloakProperties.PREFIX)
public class KeycloakProperties {

    public static final String PREFIX = "spring-boot-start-project.security.keycloak";

    /**
     * Keycloak base URL without the realm part, for example
     * https://example.com/iam or https://iam.example.com.
     */
    private String url;

    /**
     * Realm that holds the application users and clients.
     */
    private String realm;

    /**
     * Expected token "iss" claim, normally url + /realms/ + realm.
     */
    private String issuerUrl;

    /**
     * URL of the realm public signing keys (JWKS).
     */
    private String jwkSetUrl;

    /**
     * Client ID of the API (resource server) client. Its client roles are
     * mapped to Spring Security roles.
     */
    private String apiClientId;

    /**
     * Value the token "aud" claim must contain, normally the API client ID.
     */
    private String audience;

    /**
     * Client ID of the browser application (public client), handed over to the
     * SPA.
     */
    private String spaClientId;

    /**
     * JWT claim used as the authenticated principal name.
     */
    private String principalClaim;
}
