package info.setmy.stealer.services;

import info.setmy.stealer.models.StealerConfig;
import info.setmy.stealer.models.StepConfig;
import org.apache.commons.io.FileUtils;
import info.setmy.stealer.models.Change;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static info.setmy.vcs.models.RepoType.GIT;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.io.FileUtils.deleteDirectory;
import static org.assertj.core.api.Assertions.assertThat;

public class StealerServiceIT {

    public static final String TARGET_DIR = "target/";
    public static final String TEST_DATA_DIR = TARGET_DIR + "test-data/";
    public static final String PATCHES_DIR = TARGET_DIR + "test-classes/patches/";
    public static final String STEALER_TEST_WORKING_DIR = TEST_DATA_DIR + StealerServiceIT.class.getSimpleName();
    public static final String STEALER_DIR = STEALER_TEST_WORKING_DIR + "/.stealer";
    public static final String CLONE_DIR = STEALER_DIR + "/clones";
    public static final String COPY_DIR = STEALER_DIR + "/copy";
    public static final String FINAL_DIR = STEALER_DIR + "/final";
    public static final String A_CLONE_DIR = CLONE_DIR + "/stealer-test-a";
    public static final String B_CLONE_DIR = CLONE_DIR + "/stealer-test-b";
    public static final String A_COPY_DIR = COPY_DIR + "/stealer-test-a";
    public static final String B_COPY_DIR = COPY_DIR + "/stealer-test-b";

    StealerService stealerService;

    @BeforeEach
    public void setUp() throws IOException {
        stealerService = new StealerService();
        deleteDirectory(new File(CLONE_DIR));
        deleteDirectory(new File(COPY_DIR));
        deleteDirectory(new File(FINAL_DIR));
    }

    @Test
    public void testStealerService() {
        final List<String> aCleanup = new ArrayList<>();
        aCleanup.add("a");
        aCleanup.add("b/b.txt");
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
        final StealerConfig stealerConfig = StealerConfig.builder()
            .workingDirectoryString(STEALER_TEST_WORKING_DIR)
            .stepConfig(stepConfigA)
            .stepConfig(stepConfigB)
            .build();

        stealerService.steal(stealerConfig);

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

    @Test
    public void testStealerServiceWithChange() {
        final Change change = Change.builder()
            .pattern("this should be replaced")
            .replacement("this has been changed")
            .build();
        final StepConfig stepConfigA = StepConfig.builder()
            .repoType(GIT)
            .url(toUrl("https://github.com/setmy-info/stealer-test-a.git"))
            .branchName("master")
            .directoryName("stealer-test-a")
            .changes(List.of(change))
            .build();
        final StealerConfig stealerConfig = StealerConfig.builder()
            .workingDirectoryString(STEALER_TEST_WORKING_DIR)
            .stepConfig(stepConfigA)
            .build();

        stealerService.steal(stealerConfig);

        final File changedFile = toFile(A_COPY_DIR, "test.txt");
        assertThat(changedFile).exists().isFile();
        assertThat(content(changedFile)).contains("this has been changed");
        assertThat(content(changedFile)).doesNotContain("this should be replaced");
        assertThat(toFile(STEALER_TEST_WORKING_DIR, "test.txt")).exists().isFile();
    }

    @Test
    @EnabledOnOs(OS.LINUX)
    public void testStealerServiceWithPatch() {
        final List<String> aCleanup = new ArrayList<>();
        aCleanup.add("a");
        final List<String> aPatches = new ArrayList<>();
        aPatches.add(new File(PATCHES_DIR, "stealer-test-a.patch").getAbsolutePath());
        final StepConfig stepConfigA = StepConfig.builder()
            .repoType(GIT)
            .url(toUrl("https://github.com/setmy-info/stealer-test-a.git"))
            .branchName("master")
            .directoryName("stealer-test-a")
            .cleanup(aCleanup)
            .patches(aPatches)
            .build();
        final StealerConfig stealerConfig = StealerConfig.builder()
            .workingDirectoryString(STEALER_TEST_WORKING_DIR)
            .stepConfig(stepConfigA)
            .build();

        stealerService.steal(stealerConfig);

        final File patchedFile = toFile(A_COPY_DIR, "test.txt");
        assertThat(patchedFile).exists().isFile();
        assertThat(content(patchedFile)).contains("this has been replaced");
    }

    public static String content(final File file) {
        try {
            return FileUtils.readFileToString(file, UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static File toFile(final String dir, final String subItem) {
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
