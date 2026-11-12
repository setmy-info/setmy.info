package info.setmy.microservice.web.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Maps a Keycloak access token to Spring Security authorities:
 * <ul>
 *     <li>scope claim: {@code SCOPE_<scope>} (Spring Security default)</li>
 *     <li>realm roles, {@code realm_access.roles}: {@code ROLE_<role>}</li>
 *     <li>client roles of the API client, {@code resource_access.<apiClientId>.roles}: {@code ROLE_<role>}</li>
 * </ul>
 * With that {@code hasRole('calculator-user')}, {@code @Secured("ROLE_calculator-user")} and
 * {@code @RolesAllowed("calculator-user")} work on Keycloak roles.
 */
public class KeycloakGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    public static final String ROLE_PREFIX = "ROLE_";
    public static final String REALM_ACCESS_CLAIM = "realm_access";
    public static final String RESOURCE_ACCESS_CLAIM = "resource_access";
    public static final String ROLES = "roles";

    private final JwtGrantedAuthoritiesConverter scopeConverter = new JwtGrantedAuthoritiesConverter();

    private final String apiClientId;

    public KeycloakGrantedAuthoritiesConverter(final String apiClientId) {
        this.apiClientId = apiClientId;
    }

    @Override
    public Collection<GrantedAuthority> convert(final Jwt jwt) {
        final Set<GrantedAuthority> authorities = new LinkedHashSet<>(scopeConverter.convert(jwt));
        addRoles(authorities, jwt.getClaimAsMap(REALM_ACCESS_CLAIM));
        final Map<String, Object> resourceAccess = jwt.getClaimAsMap(RESOURCE_ACCESS_CLAIM);
        if (resourceAccess != null && resourceAccess.get(apiClientId) instanceof Map<?, ?> clientAccess) {
            addRoles(authorities, clientAccess);
        }
        return authorities;
    }

    private static void addRoles(final Set<GrantedAuthority> authorities, final Map<?, ?> access) {
        if (access != null && access.get(ROLES) instanceof Collection<?> roles) {
            roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + role)));
        }
    }
}
