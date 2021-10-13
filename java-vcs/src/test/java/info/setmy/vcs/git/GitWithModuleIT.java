package info.setmy.vcs.git;

import info.setmy.vcs.RepoType;
import static info.setmy.vcs.RepoType.GIT;
import info.setmy.vcs.Vcs;
import info.setmy.vcs.VcsFactory;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import static org.junit.jupiter.api.condition.OS.WINDOWS;

/**
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class GitWithModuleIT {

    private Vcs vcsA;
    private Vcs vcsB;

    private final RepoType REPO_TYPE = GIT;
    private final String URL_A = "https://github.com/setmy-info/stealer-test-a.git";
    private final String URL_B = "https://github.com/setmy-info/stealer-test-b.git";
    private final String DIR = "target";

    @BeforeEach
    public void before() throws IOException {
        FileUtils.deleteDirectory(new File("target/module-a"));
        FileUtils.deleteDirectory(new File("target/module-b"));
        vcsA = VcsFactory.instanceOf(REPO_TYPE, URL_A, DIR, "module-a");
        vcsB = VcsFactory.instanceOf(REPO_TYPE, URL_B, DIR, "module-b");
    }

    @Test
    @DisabledOnOs(WINDOWS)
    public void initial_directories() {
        assertThat(vcsA.getCloneWorkingDirectory().getAbsolutePath()).endsWith("/java-vcs/target");
        assertThat(vcsA.getWorkingDirectory().getAbsolutePath()).endsWith("/java-vcs/target/module-a");
        assertThat(vcsB.getCloneWorkingDirectory().getAbsolutePath()).endsWith("/java-vcs/target");
        assertThat(vcsB.getWorkingDirectory().getAbsolutePath()).endsWith("/java-vcs/target/module-b");
    }

    @Test
    @EnabledOnOs(WINDOWS)
    public void initial_directoriesWin() {
        assertThat(vcsA.getCloneWorkingDirectory().getAbsolutePath()).endsWith("\\java-vcs\\target");
        assertThat(vcsA.getWorkingDirectory().getAbsolutePath()).endsWith("\\target\\module-a");
        assertThat(vcsB.getCloneWorkingDirectory().getAbsolutePath()).endsWith("\\java-vcs\\target");
        assertThat(vcsB.getWorkingDirectory().getAbsolutePath()).endsWith("\\target\\module-b");
    }

    @Test
    public void clone_and_update() {
        vcsA.doClone();
        vcsA.doCheckout("master");
        assertThat(FileUtils.isDirectory(new File("target/module-a"), NOFOLLOW_LINKS)).isTrue();

        vcsB.doClone();
        vcsB.doCheckout("develop");
        assertThat(FileUtils.isDirectory(new File("target/module-b"), NOFOLLOW_LINKS)).isTrue();
    }
}
