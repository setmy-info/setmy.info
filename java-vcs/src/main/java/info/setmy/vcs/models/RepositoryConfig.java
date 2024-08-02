package info.setmy.vcs.models;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.net.URL;

@Getter
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class RepositoryConfig {

    private final RepoType repoType;
    private final URL url;
    private final File cloningDirectory;
    private final String directoryName;

    public File getClonedDirectory() {
        return new File(cloningDirectory, directoryName);
    }
}
