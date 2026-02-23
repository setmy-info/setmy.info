package info.setmy.stealer.services;

import info.setmy.stealer.models.Change;
import info.setmy.stealer.models.StepConfig;
import info.setmy.vcs.models.RepoType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StepConfigMapperTest {

    private StepConfigMapper stepConfigMapper;

    @BeforeEach
    void setUp() {
        stepConfigMapper = StepConfigMapper.getInstance();
    }

    @Test
    void toStepInnerConfig_shouldMapAllFields() throws MalformedURLException {
        final URL url = URI.create("https://github.com/test/repo.git").toURL();
        final StepConfig stepConfig = StepConfig.builder()
            .repoType(RepoType.GIT)
            .url(url)
            .directoryName("test-repo")
            .branchName("main")
            .subDirectories(List.of("src", "test"))
            .cleanup(List.of("target", ".git"))
            .patches(List.of("patch1.patch"))
            .build();

        final StepInnerConfig result = stepConfigMapper.toStepInnerConfig(stepConfig);

        assertThat(result.getRepoType()).isEqualTo(RepoType.GIT);
        assertThat(result.getUrl()).isEqualTo(url);
        assertThat(result.getDirectoryName()).isEqualTo("test-repo");
        assertThat(result.getBranchName()).isEqualTo("main");
        assertThat(result.getSubDirectories()).containsExactly("src", "test");
        assertThat(result.getCleanup()).containsExactly("target", ".git");
        assertThat(result.getPatches()).containsExactly("patch1.patch");
    }

    @Test
    void toStepInnerConfig_shouldMapChanges() throws MalformedURLException {
        final URL url = URI.create("https://github.com/test/repo.git").toURL();
        final Change change = Change.builder().pattern("old").replacement("new").build();
        final StepConfig stepConfig = StepConfig.builder()
            .repoType(RepoType.GIT)
            .url(url)
            .directoryName("test-repo")
            .changes(List.of(change))
            .build();

        final StepInnerConfig result = stepConfigMapper.toStepInnerConfig(stepConfig);

        assertThat(result.getChanges()).hasSize(1);
        assertThat(result.getChanges().get(0).getPattern()).isEqualTo("old");
        assertThat(result.getChanges().get(0).getReplacement()).isEqualTo("new");
    }

    @Test
    void toStepInnerConfig_shouldHandleNullLists() throws MalformedURLException {
        final URL url = URI.create("https://github.com/test/repo.git").toURL();
        final StepConfig stepConfig = StepConfig.builder()
            .repoType(RepoType.GIT)
            .url(url)
            .directoryName("test-repo")
            .branchName("main")
            .build();

        final StepInnerConfig result = stepConfigMapper.toStepInnerConfig(stepConfig);

        assertThat(result.getSubDirectories()).isEmpty();
        assertThat(result.getCleanup()).isEmpty();
        assertThat(result.getPatches()).isEmpty();
        assertThat(result.getChanges()).isEmpty();
    }

    @Test
    void toStepInnerConfigList_shouldMapList() throws MalformedURLException {
        final URL url = URI.create("https://github.com/test/repo.git").toURL();
        final StepConfig stepConfig = StepConfig.builder()
            .repoType(RepoType.GIT)
            .url(url)
            .directoryName("test-repo")
            .build();

        final List<StepInnerConfig> result = stepConfigMapper.toStepInnerConfigList(List.of(stepConfig));

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getDirectoryName()).isEqualTo("test-repo");
    }
}
