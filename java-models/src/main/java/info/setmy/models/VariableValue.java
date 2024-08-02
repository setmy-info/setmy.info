package info.setmy.models;

import java.util.Optional;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class VariableValue extends VariableObject<String, String> {

    public static final String EMPTY = "";

    public VariableValue(final String name, final String value) {
        super(name, value);
    }

    public String getName() {
        return getNameOptional().orElse(EMPTY);
    }

    public Optional<String> getNameOptional() {
        return super.getKeyOptional();
    }

    public String getValue() {
        return getValueOptional().orElse(EMPTY);
    }

    public Optional<String> getValueOptional() {
        return super.getObjectOptional();
    }
}
