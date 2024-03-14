package info.setmy.microservice;

import org.junit.Test;

import static info.setmy.microservice.Switches.VINTAGE_UNIT_TEST_FAILS;
import static org.assertj.core.api.Assertions.fail;

public class VintageExampleTest {

    @Test
    public void test() {
        if (VINTAGE_UNIT_TEST_FAILS) {
            fail("Vintage unit test fail");
        }
    }
}
