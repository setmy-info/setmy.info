package info.setmy.microservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class ExampleLog4j2Test {

    private static final Logger LOG = LogManager.getLogger(ExampleLog4j2Test.class);

    @Test
    public void foo() {
        LOG.debug("Hello debug World!");
    }
}
