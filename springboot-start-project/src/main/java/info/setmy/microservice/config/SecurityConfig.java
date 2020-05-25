package info.setmy.microservice.config;

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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/home",
                        "/rest/hello",
                        "/api/example",
                        "/actuator/**"
                ).permitAll()
                .anyRequest()
                .authenticated();
    }
}
