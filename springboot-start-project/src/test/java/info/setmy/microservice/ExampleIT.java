package info.setmy.microservice;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static info.setmy.microservice.Switches.INTEGRATION_TEST_FAILS;
import static org.assertj.core.api.Assertions.fail;

@Slf4j
public class ExampleIT {

    @Test
    public void test() {
        log.info("Logging INTEGRATION_TEST_FAILS {}", INTEGRATION_TEST_FAILS);
        if (INTEGRATION_TEST_FAILS) {
            fail("Integration test fail");
        }
    }
}
