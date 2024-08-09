package info.setmy.stealer.services;

import info.setmy.stealer.models.StepConfig;

import java.util.ArrayList;
import java.util.List;

import static java.util.List.copyOf;
import static java.util.stream.Collectors.toUnmodifiableList;

public class StepConfigMapper {

    public final static StepConfigMapper INSTANCE = new StepConfigMapper();

    public static StepConfigMapper getInstance() {
        return INSTANCE;
    }

    public List<StepInnerConfig> toStepInnerConfigList(final List<StepConfig> stepConfigs) {
        return stepConfigs.stream()
            .map(stepConfig -> toStepInnerConfig(stepConfig))
            .collect(toUnmodifiableList());
    }

    public StepInnerConfig toStepInnerConfig(final StepConfig stepConfig) {
        return StepInnerConfig.builder()
            .repoType(stepConfig.getRepoType())
            .url(stepConfig.getUrl())
            .directoryName(stepConfig.getDirectoryName())
            .branchName(stepConfig.getBranchName())
            .subDirectories(replaceEmpty(stepConfig.getSubDirectories()))
            .cleanup(replaceEmpty(stepConfig.getCleanup()))
            .build();
    }

    private List<String> replaceEmpty(final List<String> subDirectories) {
        if (subDirectories == null) {
            return new ArrayList<>();
        }
        return copyOf(subDirectories);
    }
}
