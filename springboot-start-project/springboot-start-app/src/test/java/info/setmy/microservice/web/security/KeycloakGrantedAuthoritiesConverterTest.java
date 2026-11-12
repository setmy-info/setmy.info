package info.setmy.microservice.web.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class KeycloakGrantedAuthoritiesConverterTest {

    private static final String API_CLIENT_ID = "springboot-start-app-api";

    private final KeycloakGrantedAuthoritiesConverter converter = new KeycloakGrantedAuthoritiesConverter(API_CLIENT_ID);

    @Test
    void mapsScopesRealmRolesAndApiClientRoles() {
        final Jwt jwt = jwt()
            .claim("scope", "openid calculator")
            .claim("realm_access", Map.of("roles", List.of("offline_access")))
            .claim("resource_access", Map.of(
                API_CLIENT_ID, Map.of("roles", List.of("calculator-user")),
                "another-client", Map.of("roles", List.of("another-role"))
            ))
            .build();

        assertThat(authorities(jwt)).containsExactlyInAnyOrder(
            "SCOPE_openid", "SCOPE_calculator", "ROLE_offline_access", "ROLE_calculator-user"
        );
    }

    @Test
    void tokenWithoutRolesGivesScopesOnly() {
        final Jwt jwt = jwt().claim("scope", "openid").build();

        assertThat(authorities(jwt)).containsExactly("SCOPE_openid");
    }

    private static Jwt.Builder jwt() {
        return Jwt.withTokenValue("token").header("alg", "RS256").subject("subject");
    }

    private List<String> authorities(final Jwt jwt) {
        return converter.convert(jwt).stream().map(GrantedAuthority::getAuthority).toList();
    }
}
