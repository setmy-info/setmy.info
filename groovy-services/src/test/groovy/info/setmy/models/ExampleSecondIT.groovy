package info.setmy.models

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
class ExampleSecondIT {

    ExampleSecond example

    @BeforeEach
    void before() {
        example = new ExampleSecond()
        example.firstName = "Imre"
    }

    @Test
    void test() {
        assert example.firstName == "Imre"
    }
}
