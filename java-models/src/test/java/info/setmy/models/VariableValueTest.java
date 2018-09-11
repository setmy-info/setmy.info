package info.setmy.models;

import static info.setmy.models.VariableValue.EMPTY;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class VariableValueTest {

    VariableValue variableValue;

    @Before
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
