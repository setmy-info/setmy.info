package info.setmy.stealer.models;

import info.setmy.stealer.exceptions.StealerException;
import info.setmy.stealer.models.steps.SVCCloneStep;
import info.setmy.vcs.Vcs;
import info.setmy.vcs.VcsFactory;
import info.setmy.vcs.models.RepositoryConfig;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Getter
@Slf4j
@RequiredArgsConstructor
public class Stealer {

    public static final String STEALER_DIR = ".stealer";

    public static final String CLONES_DIR = STEALER_DIR + "/clones";

    private final List<RepositoryConfig> repositoryConfigs;

    private final String workingDirectory;

    private final List<RepositoryScript> repositoryScripts = new ArrayList<>();

    private final VcsFactory vcsFactory = VcsFactory.getInstance();

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
        repositoryConfigs.forEach(repository -> {
            try {
                final RepositoryScript repositoryScript;
                repositoryScript = new RepositoryScript(repository, repoTypeToVcs(repository));
                repositoryScript.addStep(new SVCCloneStep());// TODO move to ordinary configured steps?
                addConfiguredSteps(repositoryScript);
                repositoryScripts.add(repositoryScript);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
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

    private Vcs repoTypeToVcs(final RepositoryConfig repositoryConfig) throws MalformedURLException {
        return vcsFactory.newVcs(repositoryConfig);
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
