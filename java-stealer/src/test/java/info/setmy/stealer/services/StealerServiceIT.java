package info.setmy.stealer.services;

import info.setmy.stealer.models.StealerConfig;
import info.setmy.stealer.models.StepConfig;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static info.setmy.vcs.models.RepoType.GIT;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class StealerServiceIT {

    public static final String TARGET_DIR = "target/";
    public static final String TEST_DATA_DIR = TARGET_DIR + "test-data/";
    public static final String STEALER_TEST_WORKING_DIR = TEST_DATA_DIR + StealerServiceIT.class.getSimpleName();
    public static final String STEALER_DIR = STEALER_TEST_WORKING_DIR + "/.stealer";
    public static final String CLONE_DIR = STEALER_DIR + "/clone";
    public static final String COPY_DIR = STEALER_DIR + "/copy";
    public static final String A_CLONE_DIR = CLONE_DIR + "/stealer-test-a";
    public static final String B_CLONE_DIR = CLONE_DIR + "/stealer-test-b";
    public static final String A_COPY_DIR = COPY_DIR + "/stealer-test-a";
    public static final String B_COPY_DIR = COPY_DIR + "/stealer-test-b";

    StealerService stealerService;

    @BeforeEach
    public void setUp() throws IOException {
        stealerService = new StealerService();
        FileUtils.deleteDirectory(new File(TEST_DATA_DIR));
    }

    @Test
    public void testStealerService() throws MalformedURLException {
        final List<String> aCleanup = new ArrayList<>();
        aCleanup.add("a");
        aCleanup.add("b/b.txt");
        final StealerConfig stealerConfig = StealerConfig.builder()
            .workingDirectoryString(STEALER_TEST_WORKING_DIR)
            .build();
        final StepConfig stepConfigA = StepConfig.builder()
            .repoType(GIT)
            .url(toUrl("https://github.com/setmy-info/stealer-test-a.git"))
            .branchName("master")
            .directoryName("stealer-test-a")
            .cleanup(aCleanup)
            .build();
        final List<String> subFolders = new ArrayList<>();
        subFolders.add("subfolder");
        final StepConfig stepConfigB = StepConfig.builder()
            .repoType(GIT)
            .url(toUrl("https://github.com/setmy-info/stealer-test-b.git"))
            .branchName("develop")
            .directoryName("stealer-test-b")
            .subDirectories(subFolders)
            .build();
        stealerConfig.getStepConfigs().add(stepConfigA);
        stealerConfig.getStepConfigs().add(stepConfigB);

        stealerService.steal(stealerConfig);

        assertThat(toFile(A_CLONE_DIR, "master.txt")).exists().isFile();
        assertThat(toFile(B_CLONE_DIR, "develop.txt")).exists().isFile();
        assertThat(toFile(A_COPY_DIR, "master.txt")).exists().isFile();
        assertThat(toFile(A_COPY_DIR, "b")).exists().isDirectory();
        assertThat(toFile(A_COPY_DIR, "b/b.txt")).doesNotExist();
        assertThat(toFile(A_COPY_DIR, "a")).doesNotExist();
        assertThat(toFile(B_COPY_DIR, "root.txt")).exists().isFile();
        final String a_master = content(toFile(A_CLONE_DIR, "master.txt"));
        final String b_root = content(toFile(B_COPY_DIR, "root.txt"));
    }

    public String content(final File file) {
        try {
            return FileUtils.readFileToString(file, UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File toFile(final String dir, final String subItem) {
        return new File(new File(dir), subItem);
    }

    public static URL toUrl(final String urlString) {
        final URI uri = URI.create(urlString);
        try {
            return uri.toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}

