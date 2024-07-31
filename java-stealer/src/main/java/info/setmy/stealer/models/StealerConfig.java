package info.setmy.stealer.models;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class StealerConfig {

    private final File workingDirectory;

    private final List<StepConfig> stepConfigs = new ArrayList<>();

    public static class StealerConfigBuilder {
        public StealerConfigBuilder workingDirectoryString(final String fileName) {
            this.workingDirectory = new File(fileName);
            return this;
        }
    }
}
