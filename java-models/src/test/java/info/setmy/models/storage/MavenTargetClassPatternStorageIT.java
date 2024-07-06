package info.setmy.models.storage;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Slf4j
public class MavenTargetClassPatternStorageIT {

    private static final String STORAGE_FOLDER = ".";

    Storage storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage(STORAGE_FOLDER)
            .init();
    }

    @Test
    public void currentClassFolderStructure() {
        new File("./target/info/setmy/models/storage/MavenTargetClassPatternStorageIT/currentClassFolderStructure.txt").delete();
        new File("./target/info/setmy/models/storage/MavenTargetClassPatternStorageIT/").delete();
        var pattern = MavenTargetClassPattern.builder()
            .name("currentClassFolderStructure.txt")
            .clazz(this.getClass())
            .build();

        var file = storage.createStorageFile(pattern);

        assertThat(file).isNotEmpty();
        assertThat(file.get().getChild().getAbsolutePath().replace('\\', '/')).endsWith("./target/info/setmy/models/storage/MavenTargetClassPatternStorageIT/currentClassFolderStructure.txt");
    }
}
