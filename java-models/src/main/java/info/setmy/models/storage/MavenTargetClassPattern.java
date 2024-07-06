package info.setmy.models.storage;

import info.setmy.exceptions.InitializationException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import static info.setmy.models.storage.DirectoryStructurePattern.addString;

/**
 * Class for maven target folder class tests.
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class MavenTargetClassPattern<T> implements StoragePattern {

    private final static String TARGET_FOLDER = "target";

    private final Class<? extends T> clazz;
    private final String name;

    @Override
    public String getName() {
        return name;
    }

    private void validate() {
        if (clazz == null || name == null) {
            throw new InitializationException("class or name is null");
        }
    }

    @Override
    public String toString() {
        validate();
        final String fullClassNameWithSlashes = clazz.getName().replace('.', '/');
        final StringBuilder stringBuilder = new StringBuilder();
        addString(TARGET_FOLDER, stringBuilder);
        addString(fullClassNameWithSlashes, stringBuilder);
        return stringBuilder.toString();
    }
}
