package info.setmy;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Test
    public void test() {
        log.info("Unit test");
    }
}