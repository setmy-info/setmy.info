package info.setmy.vcs;

import info.setmy.vcs.models.RepositoryConfig;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static info.setmy.vcs.models.RepoType.HG;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class HgWithModuleIT {

    private VcsFactory vcsFactory;

    private Vcs vcs;

    private final String URL = "https://repo.mercurial-scm.org/hello";
    private final String DIR = "target";

    @BeforeEach
    public void before() throws IOException {
        FileUtils.deleteDirectory(new File("target/hello"));
        vcsFactory = VcsFactory.getInstance();
        vcs = vcsFactory.newVcs(
            RepositoryConfig.builder()
                .repoType(HG)
                .url(new URL(URL))
                .cloningDirectory(new File(DIR))
                .directoryName("hello")
                .build()
        );
    }

    @Test
    @Disabled
    public void clone_and_update() {
        vcs.doClone();
        //vcs.doCheckout("default");
        //vcs.doPull();
        //vcs.doPush();
        assertThat(FileUtils.isDirectory(new File("target/hello"), NOFOLLOW_LINKS)).isTrue();
        assertThat(new File("target/hello/hello.c").isFile()).isTrue();
    }
}
