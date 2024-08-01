package info.setmy.models.storage;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.File;

/**
 * Can present file and directory.
 * <p>
 * If directory, then child file object and child name is null.
 * <p>
 * If file, then parent file object and parent file name is null.
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
@Getter
@RequiredArgsConstructor
public class StorageFile {

    private final File parent;

    private final File child;

    private final String parentName;

    private final String childName;
}
