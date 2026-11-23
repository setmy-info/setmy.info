package info.setmy.business.logic.starter.it;

import info.setmy.business.logic.starter.BusinessLogicAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Base class of all starter integration tests (*IT.java).
 * <p>
 * Starts a Spring context with only the starter auto-configuration, the way an application gets it. A derived test
 * only autowires the beans it tests.
 */
@SpringBootTest(classes = BusinessLogicAutoConfiguration.class)
public abstract class SpringBase {
}
