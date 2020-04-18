package info.setmy.models;

import static info.setmy.models.VariableValue.EMPTY;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class VariableValueTest {

    VariableValue variableValue;

    @BeforeEach
    public void setUp() {
        variableValue = new VariableValue();
    }

    @Test
    public void emptyIsEmptyString() {
        assertThat(EMPTY, is(equalTo("")));
    }

    @Test
    public void initialStateShouldHaveNulls() {
        assertThat(variableValue.getName(), is(equalTo("")));
        assertThat(variableValue.getValue(), is(equalTo("")));
    }

    @Test
    public void initialStateWithconstructorShouldHaveCorrectValues() {
        final String name = "Name";
        final String value = "Value";
        variableValue = new VariableValue(name, value);
        assertThat(variableValue.getName(), is(equalTo(name)));
        assertThat(variableValue.getValue(), is(equalTo(value)));
    }

    @Test
    public void gettersSetters() {
        final String name = "Name";
        final String value = "Value";
        variableValue.setName(name);
        variableValue.setValue(value);
        assertThat(variableValue.getName(), is(equalTo(name)));
        assertThat(variableValue.getValue(), is(equalTo(value)));
    }
}
