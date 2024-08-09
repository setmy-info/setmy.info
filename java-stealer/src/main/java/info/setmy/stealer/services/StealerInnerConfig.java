package info.setmy.stealer.services;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@RequiredArgsConstructor
class StealerInnerConfig {

    private final File workingDirectory;
    private final File stealerDirectory;
    private final File cloneDirectory;
    private final File copyDirectory;

    private final List<StepInnerConfig> stepsConfig;
}
