package info.setmy.keycloak.starter.property;

import info.setmy.keycloak.starter.it.SpringBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Only the base SMI_IAM_* values are set by the test. Issuer, JWK set URL and audience are built from them
 * in application.yaml.
 */
@TestPropertySource(properties = {
    "SMI_IAM_URL=https://iam.example.com",
    "SMI_IAM_REALM=other-realm",
    "SMI_IAM_API_CLIENT_ID=other-api",
    "SMI_IAM_SPA_CLIENT_ID=other-spa",
    "SMI_IAM_PRINCIPAL_CLAIM=preferred_username"
})
class KeycloakPropertiesDerivedIT extends SpringBase {

    @Autowired
    private KeycloakProperties keycloakProperties;

    @Test
    void baseValuesInjected() {
        assertThat(keycloakProperties.getUrl()).isEqualTo("https://iam.example.com");
        assertThat(keycloakProperties.getRealm()).isEqualTo("other-realm");
        assertThat(keycloakProperties.getApiClientId()).isEqualTo("other-api");
        assertThat(keycloakProperties.getSpaClientId()).isEqualTo("other-spa");
        assertThat(keycloakProperties.getPrincipalClaim()).isEqualTo("preferred_username");
    }

    @Test
    void derivedValuesBuiltFromBaseValues() {
        assertThat(keycloakProperties.getIssuerUrl()).isEqualTo("https://iam.example.com/realms/other-realm");
        assertThat(keycloakProperties.getJwkSetUrl())
            .isEqualTo("https://iam.example.com/realms/other-realm/protocol/openid-connect/certs");
        assertThat(keycloakProperties.getAudience()).isEqualTo("other-api");
    }
}
