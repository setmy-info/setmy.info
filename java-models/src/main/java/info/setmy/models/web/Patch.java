package info.setmy.models.web;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class Patch {

    private final PatchOperation op;

    private final String path;

    private final Object value;

    public Patch(final PatchOperation op, final String path, final Object value) {
        this.op = op;
        this.path = path;
        this.value = value;
    }

    public String getPath() {
        return path;
    }

    public PatchOperation getOp() {
        return op;
    }

    public Object getValue() {
        return value;
    }
}
