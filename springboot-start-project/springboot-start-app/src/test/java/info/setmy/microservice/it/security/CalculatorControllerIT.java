package info.setmy.microservice.it.security;

import info.setmy.microservice.it.SpringBase;
import info.setmy.microservice.web.security.KeycloakGrantedAuthoritiesConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Map;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tokens are built like Keycloak builds them (scope, resource_access) and mapped by the real
 * KeycloakGrantedAuthoritiesConverter. Signature validation is skipped by spring-security-test jwt().
 */
class CalculatorControllerIT extends SpringBase {

    private static final String API_CLIENT_ID = "springboot-start-app-api";
    private static final String CALCULATOR = "/api/calculator/";

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private KeycloakGrantedAuthoritiesConverter authoritiesConverter;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    void authConfigIsPublic() throws Exception {
        mockMvc.perform(get("/api/public/auth-config"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.realm").value("example"))
            .andExpect(jsonPath("$.clientId").value("springboot-start-app-spa"));
    }

    @Test
    void withoutTokenIsUnauthorized() throws Exception {
        mockMvc.perform(get(CALCULATOR + "add").param("a", "2").param("b", "3"))
            .andExpect(status().isUnauthorized());
    }

    @Test
    void addNeedsAnyValidToken() throws Exception {
        mockMvc.perform(get(CALCULATOR + "add").param("a", "2").param("b", "3").with(token("")))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value(5.0));
    }

    @Test
    void subtractNeedsScope() throws Exception {
        mockMvc.perform(get(CALCULATOR + "subtract").param("a", "5").param("b", "3").with(token("openid")))
            .andExpect(status().isForbidden());
        mockMvc.perform(get(CALCULATOR + "subtract").param("a", "5").param("b", "3").with(token("openid calculator")))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value(2.0));
    }

    @Test
    void multiplyNeedsUserRole() throws Exception {
        mockMvc.perform(get(CALCULATOR + "multiply").param("a", "2").param("b", "3").with(token("calculator")))
            .andExpect(status().isForbidden());
        mockMvc.perform(get(CALCULATOR + "multiply").param("a", "2").param("b", "3").with(token("calculator", "calculator-user")))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value(6.0));
    }

    @Test
    void divideNeedsUserRoleAndNonZeroDivisor() throws Exception {
        mockMvc.perform(get(CALCULATOR + "divide").param("a", "6").param("b", "3").with(token("calculator")))
            .andExpect(status().isForbidden());
        mockMvc.perform(get(CALCULATOR + "divide").param("a", "6").param("b", "3").with(token("calculator", "calculator-user")))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value(2.0));
        mockMvc.perform(get(CALCULATOR + "divide").param("a", "6").param("b", "0").with(token("calculator", "calculator-user")))
            .andExpect(status().isBadRequest());
    }

    @Test
    void moduloNeedsAdminRole() throws Exception {
        mockMvc.perform(get(CALCULATOR + "modulo").param("a", "7").param("b", "3").with(token("calculator", "calculator-user")))
            .andExpect(status().isForbidden());
        mockMvc.perform(get(CALCULATOR + "modulo").param("a", "7").param("b", "3").with(token("calculator", "calculator-admin")))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value(1.0));
    }

    @Test
    void powerLimitsUserExponent() throws Exception {
        mockMvc.perform(get(CALCULATOR + "power").param("a", "2").param("b", "3").with(token("calculator", "calculator-user")))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value(8.0));
        mockMvc.perform(get(CALCULATOR + "power").param("a", "2").param("b", "11").with(token("calculator", "calculator-user")))
            .andExpect(status().isForbidden());
        mockMvc.perform(get(CALCULATOR + "power").param("a", "2").param("b", "11").with(token("calculator", "calculator-admin")))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value(2048.0));
    }

    @Test
    void whoAmIShowsTokenData() throws Exception {
        mockMvc.perform(get(CALCULATOR + "whoami").with(token("calculator", "calculator-user")))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.subject").value("user"))
            .andExpect(jsonPath("$.clientId").value("springboot-start-app-spa"))
            .andExpect(jsonPath("$.authorities").value(org.hamcrest.Matchers.hasItems("ROLE_calculator-user", "SCOPE_calculator")));
    }

    private RequestPostProcessor token(final String scope, final String... apiClientRoles) {
        return jwt()
            .jwt(jwt -> jwt
                .claim("scope", scope)
                .claim("azp", "springboot-start-app-spa")
                .claim("resource_access", Map.of(API_CLIENT_ID, Map.of("roles", List.of(apiClientRoles))))
            )
            .authorities(authoritiesConverter);
    }
}
