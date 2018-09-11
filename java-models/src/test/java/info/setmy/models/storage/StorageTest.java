package info.setmy.models.storage;

import info.setmy.exceptions.ForbiddenException;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class StorageTest {

    Storage storage;

    private static final String STORAGE_FOLDER = "target";

    @Before
    public void setUp() {
        storage = new Storage(STORAGE_FOLDER);
        storage.init();
    }

    @Test(expected = ForbiddenException.class)
    public void initValidation_shouldNotAllowStoragesInRoot() {
        final Storage testStorage = new Storage(" /");
        testStorage.init();
    }

    @Test(expected = ForbiddenException.class)
    public void initValidation_shouldNotAllowStoragesInHome() {
        final Storage testStorage = new Storage(" ~");
        testStorage.init();
    }

    @Test(expected = ForbiddenException.class)
    public void initValidation_shouldNotAllowStoragesInUperFolder() {
        final Storage testStorage = new Storage(" .. ");
        testStorage.init();
    }

    @Test
    public void rootFileTakeOutIsAllowed() {
        final Optional<StorageFile> file = storage.getStorageFile(" /just/for/testing ");
        assertThat(file.isPresent(), is(equalTo(false)));
    }

    @Test(expected = ForbiddenException.class)
    public void homeFileTakeOutIsNotAllowed1() {
        storage.getStorageFile(" ~/just/for/testing ");
    }

    @Test(expected = ForbiddenException.class)
    public void homeFileTakeOutIsNotAllowed2() {
        storage.getStorageFile(" just/~/for/testing ");
    }

    @Test(expected = ForbiddenException.class)
    public void uperFileTakeOutIsNotAllowed1() {
        storage.getStorageFile(" ../just/for/testing ");
    }

    @Test(expected = ForbiddenException.class)
    public void uperFileTakeOutIsNotAllowed2() {
        storage.getStorageFile(" just/../for/testing ");
    }

    @Test
    public void rootDirTakeOutIsAllowed() {
        final Optional<StorageFile> file = storage.getStorageDirectory(" /just/for/testing ");
        assertThat(file.isPresent(), is(equalTo(false)));
    }

    @Test(expected = ForbiddenException.class)
    public void homeDirTakeOutIsNotAllowed1() {
        storage.getStorageDirectory(" ~/just/for/testing ");
    }

    @Test(expected = ForbiddenException.class)
    public void homeDirTakeOutIsNotAllowed2() {
        storage.getStorageDirectory(" just/~/for/testing ");
    }

    @Test(expected = ForbiddenException.class)
    public void uperDirTakeOutIsNotAllowed1() {
        storage.getStorageDirectory(" ../just/for/testing ");
    }

    @Test(expected = ForbiddenException.class)
    public void uperDirTakeOutIsNotAllowed2() {
        storage.getStorageDirectory(" just/../for/testing ");
    }

    @Test(expected = ForbiddenException.class)
    public void creatingFileWithUperDirChangeShouldFail() {
        final DirectoryStructurePattern pattern = new DirectoryStructurePattern().setSystem("../");
        storage.createStorageFile(pattern);
    }

    @Test(expected = ForbiddenException.class)
    public void creatingFileWithHomeDirShouldFail() {
        final DirectoryStructurePattern pattern = new DirectoryStructurePattern().setSystem("~");
        storage.createStorageFile(pattern);
    }

    @Test(expected = ForbiddenException.class)
    public void creatingFileWithUperDirChangeShouldFail2() {
        final DirectoryStructurePattern pattern = new DirectoryStructurePattern().setOwner("../");
        storage.createStorageFile(pattern);
    }

    @Test(expected = ForbiddenException.class)
    public void creatingFileWithHomeDirShouldFail2() {
        final DirectoryStructurePattern pattern = new DirectoryStructurePattern().setOwner("~");
        storage.createStorageFile(pattern);
    }

    @Test(expected = ForbiddenException.class)
    public void creatingFileWithUperDirChangeShouldFail3() {
        final DirectoryStructurePattern pattern = new DirectoryStructurePattern().setSubOwner("../");
        storage.createStorageFile(pattern);
    }

    @Test(expected = ForbiddenException.class)
    public void creatingFileWithHomeDirShouldFail3() {
        final DirectoryStructurePattern pattern = new DirectoryStructurePattern().setSubOwner("~");
        storage.createStorageFile(pattern);
    }

    @Test(expected = ForbiddenException.class)
    public void creatingFileWithUperDirChangeShouldFail4() {
        final DirectoryStructurePattern pattern = new DirectoryStructurePattern().setName("../");
        storage.createStorageFile(pattern);
    }

    @Test(expected = ForbiddenException.class)
    public void creatingFileWithHomeDirShouldFail4() {
        final DirectoryStructurePattern pattern = new DirectoryStructurePattern().setName("~");
        storage.createStorageFile(pattern);
    }
}
