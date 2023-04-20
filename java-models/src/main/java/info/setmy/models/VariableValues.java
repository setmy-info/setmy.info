package info.setmy.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static info.setmy.models.VariableValue.EMPTY;

/**
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class VariableValues extends VariableObject<String, List<String>> {

    public VariableValues() {
        super(null, new ArrayList<String>());
    }

    public VariableValues(final String name) {
        super(name, new ArrayList<String>());
    }

    public String getName() {
        return getNameOptional().orElse(EMPTY);
    }

    public Optional<String> getNameOptional() {
        return super.getKeyOptional();
    }

    public void clearValues() {
        final Optional<List<String>> valuesOptional = this.getObjectOptional();
        if (valuesOptional.isPresent()) {
            valuesOptional.get().clear();
        }
    }
}
