package info.setmy.stealer.cli;

import info.setmy.stealer.cli.models.StealerCallable;
import info.setmy.stealer.services.StealerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;

import static info.setmy.stealer.services.StealerServiceIT.content;
import static info.setmy.stealer.services.StealerServiceIT.toFile;
import static java.lang.System.getProperty;
import static java.lang.System.setProperty;
import static org.apache.commons.io.FileUtils.deleteDirectory;
import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationWithChangeIT {

    public static final String TARGET_DIR = "target/";
    public static final String TEST_DATA_DIR = TARGET_DIR + "test-classes/";
    public static final String STEALER_TEST_WORKING_DIR = TEST_DATA_DIR + ApplicationWithChangeIT.class.getSimpleName();

    public static final String STEALER_DIR = STEALER_TEST_WORKING_DIR + "/.stealer";
    public static final String CLONE_DIR = STEALER_DIR + "/clones";
    public static final String COPY_DIR = STEALER_DIR + "/copy";
    public static final String FINAL_DIR = STEALER_DIR + "/final";
    public static final String A_COPY_DIR = COPY_DIR + "/stealer-test-a";

    private File originalDir;
    private File testDir;
    private String[] arguments;

    @BeforeEach
    void setUp() throws IOException {
        deleteDirectory(new File(CLONE_DIR));
        deleteDirectory(new File(COPY_DIR));
        deleteDirectory(new File(FINAL_DIR));
        originalDir = new File(getProperty("user.dir"));
        testDir = new File(STEALER_TEST_WORKING_DIR);
        setProperty("user.dir", testDir.getAbsolutePath());
        arguments = new String[]{};
    }

    @AfterEach
    void tearDown() {
        setProperty("user.dir", originalDir.getAbsolutePath());
    }

    @Test
    public void executeWithChange() {
        int exitCode = new CommandLine(
            new StealerCallable(
                StealerService.getInstance(),
                StealerConfigService.getInstance(),
                StealerValidator.getInstance()
            )
        ).execute(arguments);

        assertThat(exitCode).isZero();
        final File changedFile = toFile(A_COPY_DIR, "test.txt");
        assertThat(changedFile).exists().isFile();
        assertThat(content(changedFile)).contains("this has been changed");
        assertThat(content(changedFile)).doesNotContain("this should be replaced");
        assertThat(toFile(STEALER_TEST_WORKING_DIR, "test.txt")).exists().isFile();
    }
}
