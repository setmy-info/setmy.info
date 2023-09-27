package info.setmy.microservice.model

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
class ExampleIT {

    ExampleGroovyModel example

    @BeforeEach
    void before() {
        example = new ExampleGroovyModel(name: "Imre")
    }

    @Test
    void test() {
        assert example.name == "Imre"
    }
}
