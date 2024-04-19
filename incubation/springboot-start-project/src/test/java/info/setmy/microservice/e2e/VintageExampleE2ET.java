package info.setmy.microservice.e2e;


import org.junit.Test;

import static info.setmy.microservice.Switches.E2E_VINTAGE_INTEGRATION_TEST_FAILS;
import static org.assertj.core.api.Fail.fail;

public class VintageExampleE2ET {

    @Test
    public void test() {
        if (E2E_VINTAGE_INTEGRATION_TEST_FAILS) {
            fail("Vintage E2E test fail");
        }
    }
}
