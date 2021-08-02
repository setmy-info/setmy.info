package info.setmy.stealer.models;

import info.setmy.stealer.exceptions.StealerException;
import info.setmy.stealer.models.config.Repository;
import info.setmy.stealer.models.steps.SVCCloneStep;
import info.setmy.vcs.BaseVcs;
import info.setmy.vcs.git.Git;
import info.setmy.vcs.hg.Hg;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
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

    private final List<RepositoryScript> repositoryScripts = new ArrayList<>();

    public void init() {
        initDirectories();
        initRepositoryScripts();
    }

    private void initDirectories() {
        final File clonesDir = getClonesDir();
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
            throw new StealerException("Couldn't create directory:" + clonesDir.getAbsolutePath(), ex);
        }
    }

    private void initRepositoryScripts() {
        repositories.forEach(repository -> {
            final RepositoryScript repositoryScript = new RepositoryScript(repository, repoTypeToVcs(repository));
            repositoryScript.addStep(new SVCCloneStep());// TODO move to ordinary configured steps?
            addConfiguredSteps(repositoryScript);
            repositoryScripts.add(repositoryScript);
        });
    }

    private void addConfiguredSteps(final RepositoryScript repositoryScript) {
        // TODO
    }

    public void steal() {
        repositoryScripts.forEach(repositoryScript -> {
            repositoryScript.execute();
        });
    }

    private BaseVcs repoTypeToVcs(final Repository repository) {
        switch (repository.getRepoType()) {
            case GIT -> {
                return new Git(repository.getUrl(), getClonesDirString());
            }
            case HG -> {
                return new Hg(repository.getUrl(), getClonesDirString());
            }
            default -> throw new StealerException("Not supported repo type: " + repository.getRepoType().toString());
        }
    }

    public File getClonesDir() {
        return new File(getClonesDirString());
    }

    public String getClonesDirString() {
        final StringBuilder stringBuilder = new StringBuilder();
        fillWorkingDirectory(stringBuilder);
        stringBuilder.append(CLONES_DIR);
        return stringBuilder.toString();
    }

    private void fillWorkingDirectory(final StringBuilder stringBuilder) {
        if (workingDirectory != null) {
            // TODO : avoid ./ .\ ..\ ../ parts in working dir
            stringBuilder.append(workingDirectory).append("/");
        }
    }
}
