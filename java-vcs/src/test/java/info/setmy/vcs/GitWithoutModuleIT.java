package info.setmy.vcs;

import info.setmy.vcs.models.CloningConfig;
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
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class GitWithoutModuleIT {

    private VcsFactory vcsFactory;

    private Vcs vcsA;
    private Vcs vcsB;

    private final String URL_A = "https://github.com/setmy-info/stealer-test-a.git";
    private final String URL_B = "https://github.com/setmy-info/stealer-test-b.git";
    private final String DIR = "target";

    @BeforeEach
    public void before() throws IOException {
        FileUtils.deleteDirectory(new File("target/stealer-test-a"));
        FileUtils.deleteDirectory(new File("target/stealer-test-b"));
        vcsFactory = VcsFactory.getInstance();

        vcsA = vcsFactory.newVcs(
            CloningConfig.builder()
                .repoType(GIT)
                .url(new URL(URL_A))
                .cloningDirectory(new File(DIR))
                .build()
        );
        vcsB = vcsFactory.newVcs(
            CloningConfig.builder()
                .repoType(GIT)
                .url(new URL(URL_B))
                .cloningDirectory(new File(DIR))
                .build()
        );
    }

    @Test
    public void clone_and_update() {
        vcsA.doClone();
        vcsA.doCheckout("master");
        assertThat(FileUtils.isDirectory(new File("target/stealer-test-a"), NOFOLLOW_LINKS)).isTrue();

        vcsB.doClone();
        vcsB.doCheckout("develop");
        assertThat(FileUtils.isDirectory(new File("target/stealer-test-b"), NOFOLLOW_LINKS)).isTrue();
    }
}
