package info.setmy.models;

import static info.setmy.models.VariableValue.EMPTY;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class VariableValuesTest {

    VariableValues variableValues;

    @Test
    public void createdObjectShouldHaveEmptyNotNullName() {
        variableValues = new VariableValues();
        assertThat(variableValues.getName(), is(equalTo("")));
        assertThat(variableValues.getName(), is(equalTo(EMPTY)));
    }

    @Test
    public void setName() {
        final String valueString = "something for name";
        variableValues = new VariableValues();
        variableValues.setName(valueString);
        assertThat(variableValues.getName(), is(equalTo(valueString)));
    }

    @Test
    public void createdObjectShouldHaveExistingEmptyValueList() {
        variableValues = new VariableValues();
        assertNotNull(variableValues.getObject());
        assertTrue(variableValues.getObject().isEmpty());
    }
}
