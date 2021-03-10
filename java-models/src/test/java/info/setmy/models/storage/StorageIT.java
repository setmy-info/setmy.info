package info.setmy.models.storage;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class StorageIT {

    private static final Logger LOG = LoggerFactory.getLogger(StorageIT.class);

    private static final String STORAGE_FOLDER = "target";

    Storage storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage(STORAGE_FOLDER);
        storage.init();
    }

    public void createFile() throws IOException {
        final Optional<StorageFile> file = storage.createStorageFile(new DirectoryStructurePattern().setYear(2018).setMonth(4).setDay(23));
        assertThat(file.isPresent()).isEqualTo(true);

        final File newFile = file.get().getChild();//Is generated again
        assertThat(newFile.exists()).isEqualTo(true);
        assertThat(newFile.isFile()).isEqualTo(true);
        assertThat(newFile.getAbsolutePath().contains("2018/4/23/")).isEqualTo(true);
        assertThat(storage.getStorageDirectory("2018/4/24/").isPresent()).isEqualTo(false);
    }

    @Test
    public void getExistingFileAndNonExistingFiles() throws IOException {
        final String directoryPath = "2018/4/23/";

        final Optional<StorageFile> file = storage.createStorageFile(new DirectoryStructurePattern().setYear(2018).setMonth(4).setDay(23));

        final Optional<StorageFile> createdFile = storage.getStorageFile(directoryPath + file.get().getChildName());
        assertThat(createdFile.isPresent()).isEqualTo(true);
        final Optional<StorageFile> notexistingFile = storage.getStorageFile(directoryPath + file.get().getChildName() + "sdfsdfdsf");
        assertThat(notexistingFile.isPresent()).isEqualTo(false);
    }

    @Test
    public void creatingFileWithName() throws IOException {
        final String owner = "owner";
        final String fileName = "file.txt";
        final String storageParentName = "target/owner";
        final String storageFileName = "target/owner/file.txt";

        new File(storageFileName).delete();
        new File(storageParentName).delete();

        final Optional<StorageFile> file = storage.createStorageFile(new DirectoryStructurePattern().setOwner(owner).setName(fileName));
        assertThat(file.get().getChild().getPath()).isEqualTo(os(storageFileName));
    }

    @Test
    public void createStorageFile() throws IOException {
        final String systemName = "system";
        final String owner = "owner";
        final String subOwner = "subOwner";
        final String fileName = "file.txt";
        final String parentName = "system/owner/subOwner";
        final String storageParentName = "target/system/owner/subOwner";
        final String storageFileName = "target/system/owner/subOwner/file.txt";
        final String finalFileName = "system/owner/subOwner/file.txt";

        new File(storageFileName).delete();
        new File("target/system/owner/subOwner").delete();
        new File(storageParentName).delete();
        new File("target/system").delete();

        final Optional<StorageFile> file = storage.createStorageFile(
                new DirectoryStructurePattern().
                        setSystem(systemName).
                        setOwner(owner).
                        setSubOwner(subOwner).
                        setName(fileName)
        );

        assertThat(file.get().getParentName()).isEqualTo(parentName);
        assertThat(file.get().getChildName()).isEqualTo(fileName);
        assertThat(file.get().getParent().getPath()).isEqualTo(os(storageParentName));
        assertThat(file.get().getChild().getPath()).isEqualTo(os(storageFileName));

        final Optional<StorageFile> existingFile = storage.getStorageFile(finalFileName);
        assertThat(existingFile.isPresent()).isEqualTo(true);
        assertThat(existingFile.get().getChildName()).isEqualTo(finalFileName);
    }

    private String os(String os) {
        return new java.io.File(os).getPath();
    }
}
