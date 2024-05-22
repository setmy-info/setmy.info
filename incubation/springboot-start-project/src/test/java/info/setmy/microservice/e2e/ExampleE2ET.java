package info.setmy.microservice.e2e;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static info.setmy.microservice.Switches.E2E_TEST_FAILS;
import static org.assertj.core.api.Fail.fail;

@Slf4j
public class ExampleE2ET {

    @Test
    public void test() {
        log.info("Logging E2E_TEST_FAILS {}", E2E_TEST_FAILS);
        if (E2E_TEST_FAILS) {
            fail("E2E test fail");
        }
    }
}
