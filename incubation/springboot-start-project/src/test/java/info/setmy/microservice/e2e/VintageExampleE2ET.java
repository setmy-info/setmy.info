package info.setmy.microservice.e2e;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static info.setmy.microservice.Switches.E2E_VINTAGE_INTEGRATION_TEST_FAILS;
import static org.assertj.core.api.Fail.fail;

@Slf4j
public class VintageExampleE2ET {

    @Test
    public void test() {
        log.info("Logging E2E_VINTAGE_INTEGRATION_TEST_FAILS {}", E2E_VINTAGE_INTEGRATION_TEST_FAILS);
        if (E2E_VINTAGE_INTEGRATION_TEST_FAILS) {
            fail("Vintage E2E test fail");
        }
    }
}
