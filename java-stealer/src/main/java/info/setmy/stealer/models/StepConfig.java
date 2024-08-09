package info.setmy.stealer.models;

import info.setmy.vcs.models.RepoType;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.net.URL;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class StepConfig {
    private final RepoType repoType;
    private final URL url;
    private final String directoryName;
    private final String branchName;
    private final List<String> subDirectories;
    private final List<String> cleanup;
}
