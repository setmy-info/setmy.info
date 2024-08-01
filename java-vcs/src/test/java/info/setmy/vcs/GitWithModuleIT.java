package info.setmy.vcs;

import info.setmy.vcs.models.RepositoryConfig;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static info.setmy.vcs.models.RepoType.GIT;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class GitWithModuleIT {

    private VcsFactory vcsFactory;

    private Vcs vcsA;
    private Vcs vcsB;

    private final String URL_A = "https://github.com/setmy-info/stealer-test-a.git";
    private final String URL_B = "https://github.com/setmy-info/stealer-test-b.git";
    private final String DIR = "target";

    @BeforeEach
    public void before() throws IOException {
        FileUtils.deleteDirectory(new File("target/module-a"));
        FileUtils.deleteDirectory(new File("target/module-b"));
        vcsFactory = VcsFactory.getInstance();
        vcsA = vcsFactory.newVcs(
            RepositoryConfig.builder()
                .repoType(GIT)
                .url(new URL(URL_A))
                .cloningDirectory(new File(DIR))
                .directoryName("module-a")
                .build()
        );
        vcsB = vcsFactory.newVcs(
            RepositoryConfig.builder()
                .repoType(GIT)
                .url(new URL(URL_B))
                .cloningDirectory(new File(DIR))
                .directoryName("module-b")
                .build()
        );
    }

    @Test
    public void clone_and_update() {
        vcsA.doClone();
        vcsA.doCheckout("master");
        vcsA.doPull();
        //vcsA.doPush();
        assertThat(FileUtils.isDirectory(new File("target/module-a"), NOFOLLOW_LINKS)).isTrue();
        assertThat(new File("target/module-a/master.txt").isFile()).isTrue();
        assertThat(new File("target/module-a/develop.txt").exists()).isFalse();

        vcsB.doClone();
        vcsB.doCheckout("develop");
        assertThat(FileUtils.isDirectory(new File("target/module-b"), NOFOLLOW_LINKS)).isTrue();
        assertThat(new File("target/module-b/develop.txt").isFile()).isTrue();
        assertThat(new File("target/module-b/master.txt").exists()).isFalse();
    }
}
