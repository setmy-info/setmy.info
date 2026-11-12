package info.setmy.microservice.web.controller;

import info.setmy.microservice.web.dto.CalculationDTO;
import info.setmy.microservice.web.dto.WhoAmIDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static info.setmy.microservice.web.config.SecurityConfiguration.BEARER_JWT;
import static info.setmy.microservice.web.constant.ApiConstants.API_BASE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Calculator example: every endpoint shows another way to protect an endpoint. Roles are Keycloak client roles of
 * the API client (or realm roles), mapped to ROLE_&lt;role&gt; by KeycloakGrantedAuthoritiesConverter. Scopes are
 * mapped to SCOPE_&lt;scope&gt;. Missing or invalid token gives 401, valid token without the needed role or scope
 * gives 403. See keycloak.md.
 * <p>
 * TOKEN=... ; curl -i -H "Authorization: Bearer $TOKEN" "http://localhost:8082/api/calculator/add?a=2&amp;b=3"
 */
@Tag(name = "CalculatorController", description = "Calculator: endpoint protection examples")
@SecurityRequirement(name = BEARER_JWT)
@Log4j2
@RestController
@RequestMapping(path = CalculatorController.CALCULATOR_REST_RESOURCE, produces = APPLICATION_JSON_VALUE)
public class CalculatorController {

    public static final String CALCULATOR_REST_RESOURCE = API_BASE + "/calculator";

    public static final int USER_MAX_EXPONENT = 10;

    /**
     * Option 1: URL rule only. SecurityConfiguration requires authenticated() for /api/calculator/**, so any valid
     * token (user or machine) is enough. No annotation here.
     */
    @Operation(summary = "a + b", description = "Any valid token (URL rule in SecurityConfiguration)")
    @GetMapping("add")
    public CalculationDTO add(@RequestParam final double a, @RequestParam final double b) {
        return calculation("add", a, b, a + b);
    }

    /**
     * Option 2: OAuth2 scope. The token must contain the "calculator" client scope. Typical for machine to machine
     * clients: the scope says which API the client may call.
     */
    @Operation(summary = "a - b", description = "Scope calculator: @PreAuthorize(\"hasAuthority('SCOPE_calculator')\")")
    @PreAuthorize("hasAuthority('SCOPE_calculator')")
    @GetMapping("subtract")
    public CalculationDTO subtract(@RequestParam final double a, @RequestParam final double b) {
        return calculation("subtract", a, b, a - b);
    }

    /**
     * Option 3: role with @PreAuthorize and Spring Expression Language (SpEL). Most flexible, preferred option.
     */
    @Operation(summary = "a * b", description = "Role calculator-user: @PreAuthorize(\"hasRole('calculator-user')\")")
    @PreAuthorize("hasRole('calculator-user')")
    @GetMapping("multiply")
    public CalculationDTO multiply(@RequestParam final double a, @RequestParam final double b) {
        return calculation("multiply", a, b, a * b);
    }

    /**
     * Option 4: role with JSR-250 @RolesAllowed (jakarta.annotation.security). ROLE_ prefix is added automatically.
     * Standard Jakarta annotation, needs @EnableMethodSecurity(jsr250Enabled = true).
     */
    @Operation(summary = "a / b", description = "Role calculator-user: @RolesAllowed(\"calculator-user\")")
    @RolesAllowed("calculator-user")
    @GetMapping("divide")
    public CalculationDTO divide(@RequestParam final double a, @RequestParam final double b) {
        requireNonZero(b);
        return calculation("divide", a, b, a / b);
    }

    /**
     * Option 5: role with Spring @Secured. Full authority name with ROLE_ prefix, no expressions.
     * Needs @EnableMethodSecurity(securedEnabled = true).
     */
    @Operation(summary = "a % b", description = "Role calculator-admin: @Secured(\"ROLE_calculator-admin\")")
    @Secured("ROLE_calculator-admin")
    @GetMapping("modulo")
    public CalculationDTO modulo(@RequestParam final double a, @RequestParam final double b) {
        requireNonZero(b);
        return calculation("modulo", a, b, a % b);
    }

    /**
     * Option 6: SpEL with method arguments. Admin may use any exponent, user only up to USER_MAX_EXPONENT.
     * #b refers to the method parameter b (compiled with -parameters).
     */
    @Operation(
        summary = "a ^ b",
        description = "Role calculator-admin, or role calculator-user with b <= 10: SpEL with method arguments"
    )
    @PreAuthorize("hasRole('calculator-admin') or (hasRole('calculator-user') and #b <= "
        + USER_MAX_EXPONENT + ")")
    @GetMapping("power")
    public CalculationDTO power(@RequestParam final double a, @RequestParam final double b) {
        return calculation("power", a, b, Math.pow(a, b));
    }

    /**
     * How to read the caller from the token in a controller: @AuthenticationPrincipal Jwt gives the claims,
     * Authentication gives the principal name and mapped authorities.
     */
    @Operation(summary = "Caller info from the token", description = "Any valid token: @PreAuthorize(\"isAuthenticated()\")")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("whoami")
    public WhoAmIDTO whoAmI(@AuthenticationPrincipal final Jwt jwt, final Authentication authentication) {
        return new WhoAmIDTO(
            authentication.getName(),
            jwt.getSubject(),
            jwt.getClaimAsString("azp"),
            authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).sorted().toList(),
            jwt.getExpiresAt()
        );
    }

    private static CalculationDTO calculation(final String operation, final double a, final double b, final double result) {
        log.info("Calculator {} called with a={}, b={}", operation, a, b);
        return new CalculationDTO(operation, a, b, result);
    }

    private static void requireNonZero(final double b) {
        if (b == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Division by zero");
        }
    }
}
