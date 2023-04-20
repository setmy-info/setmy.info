package info.setmy.models;

import static info.setmy.models.VariableValue.EMPTY;
import static org.assertj.core.api.Assertions.assertThat;
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
        assertThat(variableValues.getName()).isEqualTo("");
        assertThat(variableValues.getName()).isEqualTo(EMPTY);
    }

    @Test
    public void createdObjectShouldHaveExistingEmptyValueList() {
        variableValues = new VariableValues();
        assertNotNull(variableValues.getObject());
        assertTrue(variableValues.getObject().isEmpty());
    }
}
