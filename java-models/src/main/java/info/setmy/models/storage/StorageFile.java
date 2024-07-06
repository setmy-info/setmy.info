package info.setmy.models.storage;

import java.io.File;

/**
 * Can present file and directory.
 * <p>
 * If directory, then child file object and child name is null.
 * <p>
 * If file, then parent file object and parent file name is null.
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class StorageFile {

    private final File parent;

    private final File child;

    private final String parentName;

    private final String childName;

    public StorageFile(final File parent, final File child, final String parentName, final String childName) {
        this.parent = parent;
        this.child = child;
        this.parentName = parentName;
        this.childName = childName;
    }

    public String getParentName() {
        return parentName;
    }

    public String getChildName() {
        return childName;
    }

    public File getParent() {
        return new File(parent.getPath());
    }

    public File getChild() {
        return new File(child.getPath());
    }
}
