package info.setmy.stealer.services;

import info.setmy.stealer.models.Change;
import info.setmy.vcs.models.RepoType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StepInnerConfigTest {

    @Test
    void getOptionalBranchName_withBranchName_shouldReturnPresent() {
        final StepInnerConfig stepInnerConfig = StepInnerConfig.builder()
            .repoType(RepoType.GIT)
            .directoryName("test")
            .branchName("master")
            .build();

        assertThat(stepInnerConfig.getOptionalBranchName()).isPresent();
        assertThat(stepInnerConfig.getOptionalBranchName().get()).isEqualTo("master");
        assertThat(stepInnerConfig.haveBranchName()).isTrue();
    }

    @Test
    void getOptionalBranchName_withBlankBranchName_shouldReturnEmpty() {
        final StepInnerConfig stepInnerConfig = StepInnerConfig.builder()
            .repoType(RepoType.GIT)
            .directoryName("test")
            .branchName("")
            .build();

        assertThat(stepInnerConfig.getOptionalBranchName()).isEmpty();
        assertThat(stepInnerConfig.haveBranchName()).isFalse();
    }

    @Test
    void getOptionalBranchName_withNullBranchName_shouldReturnEmpty() {
        final StepInnerConfig stepInnerConfig = StepInnerConfig.builder()
            .repoType(RepoType.GIT)
            .directoryName("test")
            .build();

        assertThat(stepInnerConfig.getOptionalBranchName()).isEmpty();
        assertThat(stepInnerConfig.haveBranchName()).isFalse();
    }

    @Test
    void haveSubDirectories_withSubDirectories_shouldReturnTrue() {
        final StepInnerConfig stepInnerConfig = StepInnerConfig.builder()
            .repoType(RepoType.GIT)
            .directoryName("test")
            .subDirectories(List.of("sub1", "sub2"))
            .build();

        assertThat(stepInnerConfig.haveSubDirectories()).isTrue();
        assertThat(stepInnerConfig.getSubDirectories()).containsExactly("sub1", "sub2");
    }

    @Test
    void haveSubDirectories_withEmptySubDirectories_shouldReturnFalse() {
        final StepInnerConfig stepInnerConfig = StepInnerConfig.builder()
            .repoType(RepoType.GIT)
            .directoryName("test")
            .subDirectories(List.of())
            .build();

        assertThat(stepInnerConfig.haveSubDirectories()).isFalse();
        assertThat(stepInnerConfig.getSubDirectories()).isEmpty();
    }

    @Test
    void haveSubDirectories_withNullSubDirectories_shouldReturnFalse() {
        final StepInnerConfig stepInnerConfig = StepInnerConfig.builder()
            .repoType(RepoType.GIT)
            .directoryName("test")
            .build();

        assertThat(stepInnerConfig.haveSubDirectories()).isFalse();
        assertThat(stepInnerConfig.getSubDirectories()).isEmpty();
    }

    @Test
    void havePatches_withPatches_shouldReturnTrue() {
        final StepInnerConfig stepInnerConfig = StepInnerConfig.builder()
            .repoType(RepoType.GIT)
            .directoryName("test")
            .patches(List.of("my.patch"))
            .build();

        assertThat(stepInnerConfig.havePatches()).isTrue();
        assertThat(stepInnerConfig.getPatches()).containsExactly("my.patch");
    }

    @Test
    void havePatches_withNullPatches_shouldReturnFalse() {
        final StepInnerConfig stepInnerConfig = StepInnerConfig.builder()
            .repoType(RepoType.GIT)
            .directoryName("test")
            .build();

        assertThat(stepInnerConfig.havePatches()).isFalse();
        assertThat(stepInnerConfig.getPatches()).isEmpty();
    }

    @Test
    void haveChanges_withChanges_shouldReturnTrue() {
        final Change change = Change.builder().pattern("old").replacement("new").build();
        final StepInnerConfig stepInnerConfig = StepInnerConfig.builder()
            .repoType(RepoType.GIT)
            .directoryName("test")
            .changes(List.of(change))
            .build();

        assertThat(stepInnerConfig.haveChanges()).isTrue();
        assertThat(stepInnerConfig.getChanges()).hasSize(1);
        assertThat(stepInnerConfig.getChanges().get(0).getPattern()).isEqualTo("old");
    }

    @Test
    void haveChanges_withNullChanges_shouldReturnFalse() {
        final StepInnerConfig stepInnerConfig = StepInnerConfig.builder()
            .repoType(RepoType.GIT)
            .directoryName("test")
            .build();

        assertThat(stepInnerConfig.haveChanges()).isFalse();
        assertThat(stepInnerConfig.getChanges()).isEmpty();
    }
}
