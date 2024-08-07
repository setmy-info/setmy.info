package info.setmy.models.storage;

import info.setmy.exceptions.ForbiddenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class StorageTest {

    Storage storage;

    private static final String STORAGE_FOLDER = "target";

    @BeforeEach
    public void setUp() {
        storage = new Storage(STORAGE_FOLDER);
        storage.init();
    }

    @Test
    public void initValidation_shouldNotAllowStoragesInRoot() {
        final Storage testStorage = new Storage(" /");
        ForbiddenException thrown = assertThrows(
            ForbiddenException.class,
            () -> testStorage.init(),
            "Expected exception, but it didn't throw"
        );
        assertThat(thrown.getMessage()).isEqualTo("File or directory '/' is not allowed");
    }

    @Test
    public void initValidation_shouldNotAllowStoragesInHome() {
        final Storage testStorage = new Storage(" ~");
        ForbiddenException thrown = assertThrows(
            ForbiddenException.class,
            () -> testStorage.init(),
            "Expected exception, but it didn't throw"
        );
        assertThat(thrown.getMessage()).isEqualTo("File or directory '~' is not allowed");
    }

    @Test
    public void initValidation_shouldNotAllowStoragesInUperFolder() {
        final Storage testStorage = new Storage(" .. ");
        ForbiddenException thrown = assertThrows(
            ForbiddenException.class,
            () -> testStorage.init(),
            "Expected exception, but it didn't throw"
        );
        assertThat(thrown.getMessage()).isEqualTo("File or directory '..' is not allowed");
    }

    @Test
    public void rootFileTakeOutIsAllowed() {
        final Optional<StorageFile> file = storage.getStorageFile(" /just/for/testing ");
        assertThat(file.isPresent()).isEqualTo(false);
    }

    @Test
    public void homeFileTakeOutIsNotAllowed1() {
        ForbiddenException thrown = assertThrows(
            ForbiddenException.class,
            () -> storage.getStorageFile(" ~/just/for/testing "),
            "Expected exception, but it didn't throw"
        );
        assertThat(thrown.getMessage()).isEqualTo("File or directory '~/just/for/testing' is not allowed");
    }

    @Test
    public void homeFileTakeOutIsNotAllowed2() {
        ForbiddenException thrown = assertThrows(
            ForbiddenException.class,
            () -> storage.getStorageFile(" just/~/for/testing "),
            "Expected exception, but it didn't throw"
        );
        assertThat(thrown.getMessage()).isEqualTo("File or directory 'just/~/for/testing' is not allowed");
    }

    @Test
    public void uperFileTakeOutIsNotAllowed1() {
        ForbiddenException thrown = assertThrows(
            ForbiddenException.class,
            () -> storage.getStorageFile(" ../just/for/testing "),
            "Expected exception, but it didn't throw"
        );
        assertThat(thrown.getMessage()).isEqualTo("File or directory '../just/for/testing' is not allowed");
    }

    @Test
    public void uperFileTakeOutIsNotAllowed2() {
        ForbiddenException thrown = assertThrows(
            ForbiddenException.class,
            () -> storage.getStorageFile(" just/../for/testing "),
            "Expected exception, but it didn't throw"
        );
        assertThat(thrown.getMessage()).isEqualTo("File or directory 'just/../for/testing' is not allowed");
    }

    @Test
    public void rootDirTakeOutIsAllowed() {
        final Optional<StorageFile> file = storage.getStorageDirectory(" /just/for/testing ");
        assertThat(file.isPresent()).isEqualTo(false);
    }

    @Test
    public void homeDirTakeOutIsNotAllowed1() {
        ForbiddenException thrown = assertThrows(
            ForbiddenException.class,
            () -> storage.getStorageDirectory(" ~/just/for/testing "),
            "Expected exception, but it didn't throw"
        );
        assertThat(thrown.getMessage()).isEqualTo("File or directory '~/just/for/testing' is not allowed");
    }

    @Test
    public void homeDirTakeOutIsNotAllowed2() {
        ForbiddenException thrown = assertThrows(
            ForbiddenException.class,
            () -> storage.getStorageDirectory(" just/~/for/testing "),
            "Expected exception, but it didn't throw"
        );
        assertThat(thrown.getMessage()).isEqualTo("File or directory 'just/~/for/testing' is not allowed");
    }

    @Test
    public void uperDirTakeOutIsNotAllowed1() {
        ForbiddenException thrown = assertThrows(
            ForbiddenException.class,
            () -> storage.getStorageDirectory(" ../just/for/testing "),
            "Expected exception, but it didn't throw"
        );
        assertThat(thrown.getMessage()).isEqualTo("File or directory '../just/for/testing' is not allowed");
    }

    @Test
    public void uperDirTakeOutIsNotAllowed2() {
        ForbiddenException thrown = assertThrows(
            ForbiddenException.class,
            () -> storage.getStorageDirectory(" just/../for/testing "),
            "Expected exception, but it didn't throw"
        );
        assertThat(thrown.getMessage()).isEqualTo("File or directory 'just/../for/testing' is not allowed");
    }

    @Test
    public void creatingFileWithUperDirChangeShouldFail() {
        final StorageFileCreationPattern pattern = DirectoryStructureFileCreationPattern.builder()
            .system("../")
            .build();
        ForbiddenException thrown = assertThrows(
            ForbiddenException.class,
            () -> storage.createStorageFile(pattern),
            "Expected exception, but it didn't throw"
        );
        assertThat(thrown.getMessage()).isEqualTo("File or directory '../' is not allowed");
    }

    @Test
    public void creatingFileWithHomeDirShouldFail() {
        final StorageFileCreationPattern pattern = DirectoryStructureFileCreationPattern.builder().system("~").build();
        ForbiddenException thrown = assertThrows(
            ForbiddenException.class,
            () -> storage.createStorageFile(pattern),
            "Expected exception, but it didn't throw"
        );
        assertThat(thrown.getMessage()).isEqualTo("File or directory '~' is not allowed");
    }

    @Test
    public void creatingFileWithUperDirChangeShouldFail2() {
        final StorageFileCreationPattern pattern = DirectoryStructureFileCreationPattern.builder().subOwner("../").build();
        ForbiddenException thrown = assertThrows(
            ForbiddenException.class,
            () -> storage.createStorageFile(pattern),
            "Expected exception, but it didn't throw"
        );
        assertThat(thrown.getMessage()).isEqualTo("File or directory '../' is not allowed");
    }

    @Test
    public void creatingFileWithHomeDirShouldFail2() {
        final StorageFileCreationPattern pattern = DirectoryStructureFileCreationPattern.builder().subOwner("~").build();
        ForbiddenException thrown = assertThrows(
            ForbiddenException.class,
            () -> storage.createStorageFile(pattern),
            "Expected exception, but it didn't throw"
        );
        assertThat(thrown.getMessage()).isEqualTo("File or directory '~' is not allowed");
    }

    @Test
    public void creatingFileWithUperDirChangeShouldFail3() {
        final StorageFileCreationPattern pattern = DirectoryStructureFileCreationPattern.builder().subOwner("../").build();
        ForbiddenException thrown = assertThrows(
            ForbiddenException.class,
            () -> storage.createStorageFile(pattern),
            "Expected exception, but it didn't throw"
        );
        assertThat(thrown.getMessage()).isEqualTo("File or directory '../' is not allowed");
    }

    @Test
    public void creatingFileWithHomeDirShouldFail3() {
        final StorageFileCreationPattern pattern = DirectoryStructureFileCreationPattern.builder().subOwner("~").build();
        ForbiddenException thrown = assertThrows(
            ForbiddenException.class,
            () -> storage.createStorageFile(pattern),
            "Expected exception, but it didn't throw"
        );
        assertThat(thrown.getMessage()).isEqualTo("File or directory '~' is not allowed");
    }

    @Test
    public void creatingFileWithUperDirChangeShouldFail4() {
        final StorageFileCreationPattern pattern = DirectoryStructureFileCreationPattern.builder().name("../").build();
        ForbiddenException thrown = assertThrows(
            ForbiddenException.class,
            () -> storage.createStorageFile(pattern),
            "Expected exception, but it didn't throw"
        );
        assertThat(thrown.getMessage()).contains("File or directory '..");
        assertThat(thrown.getMessage()).contains("' is not allowed");
    }

    @Test
    public void creatingFileWithHomeDirShouldFail4() {
        final StorageFileCreationPattern pattern = DirectoryStructureFileCreationPattern.builder().name("~").build();
        ForbiddenException thrown = assertThrows(
            ForbiddenException.class,
            () -> storage.createStorageFile(pattern),
            "Expected exception, but it didn't throw"
        );
        assertThat(thrown.getMessage()).isEqualTo("File or directory '~' is not allowed");
    }
}
