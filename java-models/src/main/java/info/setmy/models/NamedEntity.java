package info.setmy.models;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 * @param <T> entity ID type.
 */
public abstract class NamedEntity<T> extends Entity<T> {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
