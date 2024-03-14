package info.setmy.microservice;

import org.junit.jupiter.api.Test;

import static info.setmy.microservice.Switches.INTEGRATION_TEST_FAILS;
import static org.assertj.core.api.Assertions.fail;

public class ExampleIT {

    @Test
    public void test() {
        if (INTEGRATION_TEST_FAILS) {
            fail("Integration test fail");
        }
    }
}
