package info.setmy.microservice;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static info.setmy.microservice.Switches.VINTAGE_UNIT_TEST_FAILS;
import static org.assertj.core.api.Assertions.fail;

@Slf4j
public class VintageExampleTest {

    @Test
    public void test() {
        log.info("Logging VINTAGE_UNIT_TEST_FAILS {}", VINTAGE_UNIT_TEST_FAILS);
        if (VINTAGE_UNIT_TEST_FAILS) {
            fail("Vintage unit test fail");
        }
    }
}
