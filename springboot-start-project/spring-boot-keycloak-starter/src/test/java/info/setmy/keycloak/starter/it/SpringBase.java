package info.setmy.keycloak.starter.it;

import info.setmy.keycloak.starter.KeycloakAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Base class of all starter integration tests (*IT.java).
 * <p>
 * Starts a Spring context with only the starter auto-configuration, the way an application gets it.
 * Each test sets its own SMI_IAM_* values with {@code @TestPropertySource}, src/test/resources/application.yaml
 * holds only the mapping to the model class and the derived values.
 */
@SpringBootTest(classes = KeycloakAutoConfiguration.class)
public abstract class SpringBase {
}
