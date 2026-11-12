package info.setmy.microservice.web.dto;

/**
 * Public browser application (SPA) login settings, so the SPA does not hardcode environment specific values.
 *
 * @param url      Keycloak base URL
 * @param realm    Keycloak realm
 * @param clientId SPA (public) client ID
 */
public record AuthConfigDTO(String url, String realm, String clientId) {
}
