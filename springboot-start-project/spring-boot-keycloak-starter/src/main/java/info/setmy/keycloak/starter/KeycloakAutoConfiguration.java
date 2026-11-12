package info.setmy.keycloak.starter;

import info.setmy.keycloak.starter.property.KeycloakProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@AutoConfiguration
@EnableConfigurationProperties(KeycloakProperties.class)
public class KeycloakAutoConfiguration {

}
