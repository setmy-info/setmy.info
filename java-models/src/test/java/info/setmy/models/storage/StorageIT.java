package info.setmy.models.storage;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
@Slf4j
public class StorageIT {

    private static final String STORAGE_FOLDER = "target";

    Storage storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage(STORAGE_FOLDER)
            .init();
    }

    @Test
    public void createFile() {
        final Optional<StorageFile> file = storage.createStorageFile(
            DirectoryStructureFileCreationPattern.builder()
                .year(2018)
                .month(4)
                .day(23)
                .build()
        );
        assertThat(file.isPresent()).isEqualTo(true);

        final File newFile = file.get().getChild();//Is generated again
        assertThat(newFile.exists()).isEqualTo(true);
        assertThat(newFile.isFile()).isEqualTo(true);
        assertThat(newFile.getAbsolutePath().replace("\\", "/").contains("2018/4/23/")).isEqualTo(true);
        assertThat(storage.getStorageDirectory("2018/4/24/").isPresent()).isEqualTo(false);
    }

    @Test
    public void getExistingFileAndNonExistingFiles() {
        final String directoryPath = "2018/4/23/";

        final Optional<StorageFile> file = storage.createStorageFile(
            DirectoryStructureFileCreationPattern.builder().build().setYear(2018).setMonth(4).setDay(23)
        );

        final Optional<StorageFile> createdFile = storage.getStorageFile(directoryPath + file.get().getChildName());
        assertThat(createdFile.isPresent()).isEqualTo(true);
        final Optional<StorageFile> notexistingFile = storage.getStorageFile(directoryPath + file.get().getChildName() + "sdfsdfdsf");
        assertThat(notexistingFile.isPresent()).isEqualTo(false);
    }

    @Test
    public void creatingFileWithName() {
        final String owner = "owner";
        final String fileName = "file.txt";
        final String storageParentName = "target/owner";
        final String storageFileName = "target/owner/file.txt";

        new File(storageFileName).delete();
        new File(storageParentName).delete();

        final Optional<StorageFile> file = storage.createStorageFile(DirectoryStructureFileCreationPattern.builder()
            .owner(owner)
            .name(fileName)
            .build()
        );
        assertThat(file.get().getChild().getPath()).isEqualTo(os(storageFileName));
    }

    @Test
    public void createStorageFile() {
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
            DirectoryStructureFileCreationPattern.builder()
                .system(systemName)
                .owner(owner)
                .subOwner(subOwner)
                .name(fileName)
                .build()
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
