package info.setmy.microservice.model

import org.junit.Before
import org.junit.Test

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
class ExampleGroovyModelVintageTest {

    ExampleGroovyModel example

    @Before
    void before() {
        example = new ExampleGroovyModel()
        example.name = "Imre"
    }

    @Test
    void test() {
        assert example.name == "Imre"
    }
}

