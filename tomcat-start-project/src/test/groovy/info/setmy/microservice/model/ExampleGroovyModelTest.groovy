package info.setmy.microservice.model

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
class ExampleGroovyModelTest {

    ExampleGroovyModel example
    
    @BeforeEach
    void before() {
        example = new ExampleGroovyModel()
        example.name = "Imre"
    }

    @Test
    void test() {
        assert example.name == "Imre"
    }
}

