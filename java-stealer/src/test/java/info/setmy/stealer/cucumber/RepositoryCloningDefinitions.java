package info.setmy.stealer.cucumber;

import info.setmy.stealer.Stealer;
import info.setmy.vcs.models.RepoType;
import info.setmy.vcs.models.RepositoryConfig;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static info.setmy.stealer.Stealer.CLONES_DIR;
import static info.setmy.stealer.Stealer.STEALER_DIR;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Slf4j
public class RepositoryCloningDefinitions {

    private final String TEST_DATA_DIR = "test-data";

    private final String TEST_DATA_STEALER_DIR = TEST_DATA_DIR + "/" + STEALER_DIR;

    private final String TEST_DATA_CLONES_DIR = TEST_DATA_DIR + "/" + CLONES_DIR;

    private final List<RepositoryConfig> repositories = new ArrayList<>();

    private File testDataDir;

    private File testDataStealerDir;

    private String testDataDirString;

    private String testDataStealerDirString;

    private Stealer stealer;

    @Before
    public void before() throws IOException {
        repositories.clear();
        log.info("Before scenario creating test data folder: {}", TEST_DATA_DIR);

        final Path testDataPath = Paths.get(TEST_DATA_DIR);
        testDataDir = testDataPath.toFile();
        testDataDirString = testDataDir.getAbsolutePath();

        final Path testDataStealerPath = Paths.get(TEST_DATA_STEALER_DIR);
        testDataStealerDir = testDataStealerPath.toFile();
        testDataStealerDirString = testDataStealerDir.getAbsolutePath();

        FileUtils.deleteDirectory(testDataStealerDir);
        FileUtils.deleteDirectory(testDataDir);

        Files.createDirectories(testDataPath);
        Files.createDirectories(testDataStealerPath);
        log.info("Before scenario created test data folder: {}", testDataDirString);
        log.info("Before scenario created test data stealer folder: {}", testDataStealerDirString);
        stealer = new Stealer(repositories, testDataDirString);
    }

    @Given("{repoType} repository {string} with short name {string}")
    public void repository(final RepoType repoType, final String url, final String name) throws MalformedURLException {
        repositories.add(RepositoryConfig.builder()
            .repoType(repoType)
            .url(new URL(url))
            .cloningDirectory(new File(TEST_DATA_CLONES_DIR))
            .directoryName(name)
            .build()
        );
    }

    @When("initializing")
    public void init() {
        stealer.init();
    }

    @When("stealing")
    public void stealing() {
        stealer.steal();
    }

    @Then("{string} folder should exist")
    public void folderShouldExist(final String folderName) {
        final String testableDir = stealer.getClonesDirString();
        log.info("Checking directory: {}", testableDir);
        assertThat(new File(testableDir).isDirectory()).isTrue();
        assertThat(new File(testableDir + "/" + folderName).isDirectory()).isTrue();
    }
}
