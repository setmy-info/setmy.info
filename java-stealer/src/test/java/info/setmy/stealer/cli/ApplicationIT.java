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

public class ApplicationIT {

    public static final String TARGET_DIR = "target/";
    public static final String TEST_DATA_DIR = TARGET_DIR + "test-classes/";
    public static final String STEALER_TEST_WORKING_DIR = TEST_DATA_DIR + ApplicationIT.class.getSimpleName();

    public static final String STEALER_DIR = STEALER_TEST_WORKING_DIR + "/.stealer";
    public static final String CLONE_DIR = STEALER_DIR + "/clones";
    public static final String COPY_DIR = STEALER_DIR + "/copy";
    public static final String FINAL_DIR = STEALER_DIR + "/final";
    public static final String A_CLONE_DIR = CLONE_DIR + "/stealer-test-a";
    public static final String B_CLONE_DIR = CLONE_DIR + "/stealer-test-b";
    public static final String A_COPY_DIR = COPY_DIR + "/stealer-test-a";
    public static final String B_COPY_DIR = COPY_DIR + "/stealer-test-b";

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
    public void execute() {
        int exitCode = new CommandLine(
            new StealerCallable(
                StealerService.getInstance(),
                StealerConfigService.getInstance(),
                StealerValidator.getInstance()
            )
        ).execute(arguments);

        assertThat(exitCode).isZero();
        assertThat(toFile(A_CLONE_DIR, "master.txt")).exists().isFile();
        assertThat(toFile(B_CLONE_DIR, "develop.txt")).exists().isFile();
        assertThat(toFile(A_COPY_DIR, "master.txt")).exists().isFile();
        assertThat(toFile(A_COPY_DIR, "b")).exists().isDirectory();
        assertThat(toFile(A_COPY_DIR, "b/b.txt")).doesNotExist();
        assertThat(toFile(A_COPY_DIR, "a")).doesNotExist();
        assertThat(toFile(B_COPY_DIR, "root.txt")).exists().isFile();
        assertThat(toFile(STEALER_TEST_WORKING_DIR, "master.txt")).exists().isFile();
        assertThat(toFile(STEALER_TEST_WORKING_DIR, "root.txt")).exists().isFile();
        final String a_master = content(toFile(A_CLONE_DIR, "master.txt"));
        final String b_root = content(toFile(B_COPY_DIR, "root.txt"));
    }
}
