package info.setmy.stealer.models.config;

import info.setmy.vcs.RepoType;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Getter
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class RepositoryConfig {

    private final String moduleName;

    private final String url;

    private final RepoType repoType;

    private final String cloneDirectory;

    private final String workingDirectory;
}
