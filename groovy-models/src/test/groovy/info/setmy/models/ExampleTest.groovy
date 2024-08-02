package info.setmy.models

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
class ExampleTest {

    Example example

    @BeforeEach
    void before() {
        example = new Example()
        example.firstName = "Imre"
    }

    @Test
    void test() {
        assert example.firstName == "Imre"
    }
}
