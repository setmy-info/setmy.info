package info.setmy.microservice.web.dto;

import java.time.Instant;
import java.util.List;

/**
 * @param name        principal name (preferred_username by default, service-account-&lt;client&gt; for M2M)
 * @param subject     token subject (sub), stable user or service account ID in Keycloak
 * @param clientId    client the token was issued to (azp)
 * @param authorities Spring Security authorities mapped from the token
 * @param expiresAt   token expiry
 */
public record WhoAmIDTO(String name, String subject, String clientId, List<String> authorities, Instant expiresAt) {
}
