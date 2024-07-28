package info.setmy.vcs;

import info.setmy.vcs.models.RepositoryConfig;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static info.setmy.vcs.models.RepoType.GIT;
import static org.assertj.core.api.Assertions.assertThat;

public class RepositoryConfigTest {

    RepositoryConfig repoConfig;

    @Test
    public void test() throws MalformedURLException {
        repoConfig = RepositoryConfig.builder()
            .repoType(GIT)
            .url(new URL("https://github.com/setmy-info/stealer-test-b.git"))
            .cloningDirectory(new File("target"))
            .directoryName("stealer-test-b")
            .build();

        assertThat(repoConfig.getClonedDirectory().toString())
            .contains("target")
            .endsWith("stealer-test-b");
    }
}
