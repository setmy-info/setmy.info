package info.setmy.stealer.services;

import info.setmy.vcs.Vcs;
import info.setmy.vcs.models.RepoType;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.unmodifiableList;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Getter
@Builder(toBuilder = true)
@RequiredArgsConstructor
class StepInnerConfig {

    private final RepoType repoType;
    private final URL url;
    private final String directoryName;
    private final String branchName;
    private final Vcs vcs;
    private final List<String> subDirectories;
    private final List<String> cleanup;

    public boolean haveBranchName() {
        return getOptionalBranchName().isPresent();
    }

    public Optional<String> getOptionalBranchName() {
        return isBlank(branchName) ? empty() : of(branchName);
    }

    public boolean haveSubDirectories() {
        return subDirectories != null && !subDirectories.isEmpty();
    }

    public List<String> getSubDirectories() {
        return haveSubDirectories() ? unmodifiableList(subDirectories) : unmodifiableList(new ArrayList<>());
    }
}
