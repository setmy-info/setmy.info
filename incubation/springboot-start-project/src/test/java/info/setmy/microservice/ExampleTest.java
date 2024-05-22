package info.setmy.microservice;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static info.setmy.microservice.Switches.UNIT_TEST_FAILS;
import static org.assertj.core.api.Assertions.fail;

@Slf4j
public class ExampleTest {

    @Test
    public void test() {
        log.info("Logging UNIT_TEST_FAILS {}", UNIT_TEST_FAILS);
        if (UNIT_TEST_FAILS) {
            fail("Unit test fail");
        }
    }
}
