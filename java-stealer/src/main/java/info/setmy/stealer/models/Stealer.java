package info.setmy.stealer.models;

import info.setmy.stealer.exceptions.StealerException;
import info.setmy.vcs.BaseVcs;
import info.setmy.vcs.git.Git;
import info.setmy.vcs.hg.Hg;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Getter
@Builder
@RequiredArgsConstructor
public class Stealer {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    public static final String STEALER_DIR = ".stealer";

    public static final String CLONES_DIR = STEALER_DIR + "/clones";

    private final List<Repository> repositories;

    private final String workingDirectory;

    public void doClone() {
        init();
        repositories.forEach(repository -> {
            repoTypeToVcs(repository).doClone();
        });
    }

    private void init() {
        final File clonesDir = new File(makeCloneDirString());
        if (clonesDir.exists()) {
            if (clonesDir.isDirectory()) {
                return;
            } else {
                throw new StealerException(clonesDir.getAbsolutePath() + " exists but is not directory");
            }
        }
        final Path clonesPath = Paths.get(clonesDir.getAbsolutePath());
        try {
            Files.createDirectories(clonesPath);
        } catch (IOException ex) {
            throw new StealerException("Coldn't create directory:" + clonesDir.getAbsolutePath(), ex);
        }
    }

    private BaseVcs repoTypeToVcs(final Repository repository) {
        switch (repository.getRepoType()) {
            case GIT -> {
                return new Git(repository.getUrl(), makeCloneDirString());
            }
            case HG -> {
                return new Hg(repository.getUrl(), makeCloneDirString());
            }
            default ->
                throw new StealerException("Not suported repo type: " + repository.getRepoType().toString());
        }
    }

    public String makeCloneDirString() {
        final StringBuilder stringBuilder = new StringBuilder();
        fillWorkingDirectory(stringBuilder);
        stringBuilder.append(CLONES_DIR);
        return stringBuilder.toString();
    }

    private void fillWorkingDirectory(final StringBuilder stringBuilder) {
        if (workingDirectory != null) {
            stringBuilder.append(workingDirectory).append("/");
        }
    }
}
