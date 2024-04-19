package info.setmy.microservice.e2e;

import org.junit.jupiter.api.Test;

import static info.setmy.microservice.Switches.E2E_TEST_FAILS;
import static org.assertj.core.api.Fail.fail;

public class ExampleE2ET {

    @Test
    public void test() {
        if (E2E_TEST_FAILS) {
            fail("E2E test fail");
        }
    }
}
