package info.setmy.models;

import java.util.Optional;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class VariableObject<V, O> {

    private V key;

    private O object;

    public VariableObject() {
    }

    public VariableObject(final V key, final O object) {
        this.key = key;
        this.object = object;
    }

    public V getKey() {
        return key;
    }

    public Optional<V> getKeyOptional() {
        return Optional.ofNullable(getKey());
    }

    public void setKey(V key) {
        this.key = key;
    }

    public O getObject() {
        return object;
    }

    public Optional<O> getObjectOptional() {
        return Optional.ofNullable(object);
    }

    public void setObject(O object) {
        this.object = object;
    }
}
