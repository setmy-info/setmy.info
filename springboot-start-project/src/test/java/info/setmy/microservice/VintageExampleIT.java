package info.setmy.microservice;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static info.setmy.microservice.Switches.VINTAGE_INTEGRATION_TEST_FAILS;
import static org.assertj.core.api.Assertions.fail;

@Slf4j
public class VintageExampleIT {

    @Test
    public void test() {
        log.info("Logging VINTAGE_INTEGRATION_TEST_FAILS {}", VINTAGE_INTEGRATION_TEST_FAILS);
        if (VINTAGE_INTEGRATION_TEST_FAILS) {
            fail("Vintage integration test fail");
        }
    }
}
