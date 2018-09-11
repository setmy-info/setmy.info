package info.setmy.models

import org.junit.Before
import org.junit.Test
import static org.junit.Assert.*

/**
 * Exception to roll back transactions.
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
class ExampleTest {
    
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
