package info.setmy.stealer.cli;

import info.setmy.stealer.models.StepConfig;
import info.setmy.vcs.models.RepoType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class StealerConfigMapperTest {

    private StealerConfigMapper stealerConfigMapper;

    @BeforeEach
    void setUp() {
        stealerConfigMapper = StealerConfigMapper.getInstance();
    }

    @Test
    void stepToStepConfig_shouldMapAllFields() {
        final Map<String, Object> stepMap = Map.of(
            "url", "https://github.com/test/repo.git",
            "repoType", "GIT",
            "directoryName", "test-repo",
            "branchName", "main",
            "subDirectories", List.of("src"),
            "cleanup", List.of("target"),
            "patches", List.of("test.patch")
        );

        final StepConfig result = stealerConfigMapper.stepToStepConfig(stepMap);

        assertThat(result).isNotNull();
        assertThat(result.getRepoType()).isEqualTo(RepoType.GIT);
        assertThat(result.getDirectoryName()).isEqualTo("test-repo");
        assertThat(result.getBranchName()).isEqualTo("main");
        assertThat(result.getSubDirectories()).containsExactly("src");
        assertThat(result.getCleanup()).containsExactly("target");
        assertThat(result.getPatches()).containsExactly("test.patch");
    }

    @Test
    void stepToStepConfig_shouldMapChanges() {
        final Map<String, Object> stepMap = Map.of(
            "url", "https://github.com/test/repo.git",
            "repoType", "GIT",
            "directoryName", "test-repo",
            "branchName", "main",
            "changes", List.of(
                Map.of("pattern", "old text", "replacement", "new text")
            )
        );

        final StepConfig result = stealerConfigMapper.stepToStepConfig(stepMap);

        assertThat(result).isNotNull();
        assertThat(result.getChanges()).hasSize(1);
        assertThat(result.getChanges().get(0).getPattern()).isEqualTo("old text");
        assertThat(result.getChanges().get(0).getReplacement()).isEqualTo("new text");
    }

    @Test
    void stepToStepConfig_withMissingOptionalFields_shouldReturnEmptyLists() {
        final Map<String, Object> stepMap = Map.of(
            "url", "https://github.com/test/repo.git",
            "repoType", "GIT",
            "directoryName", "test-repo",
            "branchName", "main"
        );

        final StepConfig result = stealerConfigMapper.stepToStepConfig(stepMap);

        assertThat(result).isNotNull();
        assertThat(result.getSubDirectories()).isEmpty();
        assertThat(result.getCleanup()).isEmpty();
        assertThat(result.getPatches()).isEmpty();
        assertThat(result.getChanges()).isEmpty();
    }

    @Test
    void stepToStepConfig_withNonMapInput_shouldReturnNull() {
        final Object result = stealerConfigMapper.stepToStepConfig("not-a-map");

        assertThat(result).isNull();
    }
}
