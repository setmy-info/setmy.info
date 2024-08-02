package info.setmy.models

import org.junit.Before
import org.junit.Test

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
class ExampleVintageIT {

    Example example

    @Before
    void before() {
        example = new Example()
        example.firstName = "Imre"
    }

    @Test
    void test() {
        assert example.firstName == "Imre"
    }
}
