package info.setmy.microservice.beans;

import info.setmy.microservice.properties.SecurityProperties;
import static java.lang.Boolean.TRUE;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

/**
 * https://spring.io/guides/gs/securing-web/
 * https://www.devglan.com/spring-security/spring-boot-security-rest-basic-authentication
 * https://www.baeldung.com/spring-security-basic-authentication
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityBeans {

    private final SecurityProperties securityProperties;
    //private final CorsFilter corsFilter;

    public SecurityBeans(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Bean
    protected SecurityFilterChain filterChain(final HttpSecurity httpSecurity) throws Exception {
        /*
        httpSecurity
                .authorizeRequests()
                .ignoringAntMatchers(
                        "/",
                        "favicon.ico",
                        //"/login",
                        "/home",
                        "/error",
                        //"/hello",
                        "/h2-console",
                        "/h2-console/login.do",
                        "/h2-console/test.do",
                        "/h2-console/**",
                        // Public REST
                        "/rest/hello",
                        "/api/example",
                        // Actuator
                        "/actuator/**",
                        // Swagger
                        "/v2/api-docs/**",
                        "/swagger-ui.html",
                        "/webjars/springfox-swagger-ui/**",
                        "/swagger-resources/**",
                        "/csrf"
                // Other
                ).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();

        if (!securityProperties.getCsrf().orElse(TRUE)) {
            httpSecurity.csrf().disable();
        }
        if (!securityProperties.getFrameOptions().orElse(TRUE)) {
            httpSecurity.headers().frameOptions().disable();
        }
        */
        return httpSecurity.build();
    }
}
