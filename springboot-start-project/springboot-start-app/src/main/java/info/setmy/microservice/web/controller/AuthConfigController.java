package info.setmy.microservice.web.controller;

import info.setmy.keycloak.starter.property.KeycloakProperties;
import info.setmy.microservice.web.dto.AuthConfigDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static info.setmy.microservice.web.constant.ApiConstants.PUBLIC_API_BASE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Hands the SPA its Keycloak settings. Public (no token yet at that point) and contains no secrets.
 * curl -i http://localhost:8082/api/public/auth-config
 */
@Tag(name = "AuthConfigController", description = "Browser application login settings")
@RestController
@RequestMapping(path = AuthConfigController.AUTH_CONFIG_REST_RESOURCE, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthConfigController {

    public static final String AUTH_CONFIG_REST_RESOURCE = PUBLIC_API_BASE + "/auth-config";

    private final KeycloakProperties keycloakProperties;

    @GetMapping
    public AuthConfigDTO authConfig() {
        return new AuthConfigDTO(
            keycloakProperties.getUrl(),
            keycloakProperties.getRealm(),
            keycloakProperties.getSpaClientId()
        );
    }
}
