package info.setmy.models;

import java.util.Optional;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class VariableValue extends VariableObject<String, String> {

    public static final String EMPTY = "";

    public VariableValue() {
    }

    public VariableValue(final String name, final String value) {
        super(name, value);
    }

    public void setName(final String name) {
        setKey(name);
    }

    public String getName() {
        return getNameOptional().orElse(EMPTY);
    }

    public Optional<String> getNameOptional() {
        return super.getKeyOptional();
    }

    public void setValue(final String value) {
        setObject(value);
    }

    public String getValue() {
        return getValueOptional().orElse(EMPTY);
    }

    public Optional<String> getValueOptional() {
        return super.getObjectOptional();
    }
}
