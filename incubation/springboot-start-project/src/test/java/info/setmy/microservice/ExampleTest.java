package info.setmy.microservice;

import org.junit.jupiter.api.Test;

import static info.setmy.microservice.Switches.UNIT_TEST_FAILS;
import static org.assertj.core.api.Assertions.fail;

public class ExampleTest {

    @Test
    public void test() {
        if (UNIT_TEST_FAILS) {
            fail("Unit test fail");
        }
    }
}
