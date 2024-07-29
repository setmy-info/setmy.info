package info.setmy.stealer.models.config;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class StealerCommand {

    private final String workingDirectory;

    private final List<StealerRepoConfig> stealerRepoConfigs = new ArrayList<>();
}
