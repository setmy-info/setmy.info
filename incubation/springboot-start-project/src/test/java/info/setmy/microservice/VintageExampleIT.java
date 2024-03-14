package info.setmy.microservice;


import org.junit.Test;

import static info.setmy.microservice.Switches.VINTAGE_INTEGRATION_TEST_FAILS;
import static org.assertj.core.api.Assertions.fail;

public class VintageExampleIT {

    @Test
    public void test() {
        if (VINTAGE_INTEGRATION_TEST_FAILS) {
            fail("Vintage integration test fail");
        }
    }
}
