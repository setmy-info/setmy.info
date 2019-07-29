package info.setmy;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleIT {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Test
    public void test() {
        log.info("Integration test");
    }
}
