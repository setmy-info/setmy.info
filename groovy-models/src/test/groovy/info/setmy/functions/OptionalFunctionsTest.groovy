package info.setmy.functions


import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static info.setmy.functions.OptionalFunctions.blankAsNull
import static info.setmy.functions.OptionalFunctions.blankToEmpty
import static java.util.Optional.empty

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
class OptionalFunctionsTest {

    @BeforeEach
    void before() {
    }

    @Test
    void blankAsNull_and_blankToEmpty() {
        final Optional<String> optionalString = empty();
        def result = optionalString.map blankAsNull
        def result2 = blankToEmpty optionalString
        assert result.isPresent() == false
        assert result2.isPresent() == false
    }
}
