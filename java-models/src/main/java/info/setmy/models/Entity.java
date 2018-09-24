package info.setmy.models;

import java.io.Serializable;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 * @param <T> entity ID type.
 */
public abstract class Entity<T> implements Serializable {

    private T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}
