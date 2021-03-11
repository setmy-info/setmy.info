package info.setmy.microservice.config;

import info.setmy.microservice.properties.SecurityProperties;
import static java.lang.Boolean.TRUE;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * https://spring.io/guides/gs/securing-web/
 * https://www.devglan.com/spring-security/spring-boot-security-rest-basic-authentication
 * https://www.baeldung.com/spring-security-basic-authentication
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecurityProperties securityProperties;

    public SecurityConfig(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers(
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
    }
}
