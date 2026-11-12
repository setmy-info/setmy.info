package info.setmy.microservice.web.config;

import info.setmy.keycloak.starter.property.KeycloakProperties;
import info.setmy.microservice.web.security.KeycloakGrantedAuthoritiesConverter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import static info.setmy.microservice.web.constant.ApiConstants.PUBLIC_API_BASE;
import static info.setmy.microservice.web.controller.CalculatorController.CALCULATOR_REST_RESOURCE;

/**
 * Stateless OAuth2 resource server: every protected request carries "Authorization: Bearer &lt;JWT&gt;" issued by
 * Keycloak. The token is validated locally (signature from Keycloak JWKS, issuer, audience, expiry), no session.
 * Issuer, JWKS and audience come from spring.security.oauth2.resourceserver.jwt.* in application.yaml.
 */
@SecurityScheme(
    name = SecurityConfiguration.BEARER_JWT,
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

    public static final String BEARER_JWT = "bearer-jwt";

    /**
     * Template examples that stay public. In a real project protect them or remove them.
     */
    private static final String[] PUBLIC_PATHS = {
        "/", "/home", "/hello", "/login", "/error", "/favicon.ico",
        "/example.html", "/calculator.html", "/listExample/**",
        "/api/example/**", "/api/anotherExample/**",
        "/graphql/**", "/graphiql/**",
        "/v3/api-docs", "/v3/api-docs/**", "/v3/api-docs.yaml", "/swagger-ui/**", "/swagger-ui.html",
        "/actuator", "/actuator/health/**", "/actuator/info",
        "/h2-console/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(
        final HttpSecurity http,
        final JwtAuthenticationConverter jwtAuthenticationConverter
    ) throws Exception {
        return http
            // No cookies or sessions are used for authentication, so CSRF protection is not needed
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // H2 console is shown in a frame
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(PUBLIC_API_BASE + "/**").permitAll()
                // Protection option 1: URL rule. Every calculator endpoint needs a valid token,
                // method annotations in CalculatorController add role and scope checks on top.
                .requestMatchers(CALCULATOR_REST_RESOURCE + "/**").authenticated()
                .requestMatchers(PUBLIC_PATHS).permitAll()
                // Everything not listed above is closed by default
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(resourceServer -> resourceServer
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter))
            )
            .build();
    }

    @Bean
    public KeycloakGrantedAuthoritiesConverter keycloakGrantedAuthoritiesConverter(final KeycloakProperties keycloakProperties) {
        return new KeycloakGrantedAuthoritiesConverter(keycloakProperties.getApiClientId());
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(
        final KeycloakGrantedAuthoritiesConverter keycloakGrantedAuthoritiesConverter,
        final KeycloakProperties keycloakProperties
    ) {
        final JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(keycloakGrantedAuthoritiesConverter);
        converter.setPrincipalClaimName(keycloakProperties.getPrincipalClaim());
        return converter;
    }
}
