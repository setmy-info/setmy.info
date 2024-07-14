package info.setmy.models

import org.junit.Before
import org.junit.Test

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
class ExampleSecondVintageTest {

    ExampleSecond example

    @Before
    void before() {
        example = new ExampleSecond()
        example.firstName = "Imre"
    }

    @Test
    void test() {
        assert example.firstName == "Imre"
    }
}
