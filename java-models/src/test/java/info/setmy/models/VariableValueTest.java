package info.setmy.models;

import static info.setmy.models.VariableValue.EMPTY;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class VariableValueTest {

    VariableValue variableValue;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void emptyIsEmptyString() {
        assertThat(EMPTY).isEqualTo("");
    }


    @Test
    public void initialStateWithconstructorShouldHaveCorrectValues() {
        final String name = "Name";
        final String value = "Value";
        variableValue = new VariableValue(name, value);
        assertThat(variableValue.getName()).isEqualTo(name);
        assertThat(variableValue.getValue()).isEqualTo(value);
    }
}
