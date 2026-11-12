package info.setmy.keycloak.starter.property;

import info.setmy.keycloak.starter.it.SpringBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Every SMI_IAM_* value is set by the test and must arrive unchanged in {@link KeycloakProperties}.
 * Test properties have higher priority than application.yaml, the same way environment variables do.
 */
@TestPropertySource(properties = {
    "SMI_IAM_URL=https://example.com/iam",
    "SMI_IAM_REALM=test-realm",
    "SMI_IAM_ISSUER_URL=https://issuer.example.com/realms/test-realm",
    "SMI_IAM_JWK_SET_URL=http://keycloak.internal:8080/realms/test-realm/protocol/openid-connect/certs",
    "SMI_IAM_API_CLIENT_ID=test-api",
    "SMI_IAM_AUDIENCE=test-audience",
    "SMI_IAM_SPA_CLIENT_ID=test-spa",
    "SMI_IAM_PRINCIPAL_CLAIM=sub"
})
class KeycloakPropertiesIT extends SpringBase {

    @Autowired
    private KeycloakProperties keycloakProperties;

    @Test
    void allValuesInjected() {
        assertThat(keycloakProperties.getUrl()).isEqualTo("https://example.com/iam");
        assertThat(keycloakProperties.getRealm()).isEqualTo("test-realm");
        assertThat(keycloakProperties.getIssuerUrl()).isEqualTo("https://issuer.example.com/realms/test-realm");
        assertThat(keycloakProperties.getJwkSetUrl())
            .isEqualTo("http://keycloak.internal:8080/realms/test-realm/protocol/openid-connect/certs");
        assertThat(keycloakProperties.getApiClientId()).isEqualTo("test-api");
        assertThat(keycloakProperties.getAudience()).isEqualTo("test-audience");
        assertThat(keycloakProperties.getSpaClientId()).isEqualTo("test-spa");
        assertThat(keycloakProperties.getPrincipalClaim()).isEqualTo("sub");
    }
}
