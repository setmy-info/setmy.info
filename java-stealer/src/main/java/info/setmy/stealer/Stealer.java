package info.setmy.stealer;

import info.setmy.stealer.exceptions.StealerException;
import info.setmy.stealer.models.config.StealerCommand;
import info.setmy.stealer.models.config.StealerRepoConfig;
import info.setmy.stealer.models.config.StealerStepConfig;
import info.setmy.stealer.steps.DumbStep;
import info.setmy.stealer.steps.InitStep;
import info.setmy.stealer.steps.SVCCloneStep;
import info.setmy.stealer.steps.Step;
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

    private final List<RepositoryConfig> repositoryConfigs;// = new ArrayList<>();

    private final String workingDirectory;

    private final List<Repository> repositories = new ArrayList<>();

    private final VcsFactory vcsFactory = VcsFactory.getInstance();

    public void steal(final StealerCommand stealerCommand) {
        // TODO : build and populate repositories and steps
        initRepos(stealerCommand);
        repositories.forEach(repo -> repo.execute());
    }

    private void initRepos(final StealerCommand stealerCommand) {
        final List<StealerRepoConfig> stealerRepoConfigs = stealerCommand.getStealerRepoConfigs();
        stealerRepoConfigs.forEach(stealerRepoConfig -> initRepo(stealerCommand, stealerRepoConfig));
    }

    private void initRepo(final StealerCommand stealerCommand, final StealerRepoConfig stealerRepoConfig) {
        final RepositoryConfig repositoryConfig = RepositoryConfig.builder()
            .repoType(stealerRepoConfig.getRepoType())
            .url(stealerRepoConfig.getUrl())
            .cloningDirectory(new File(stealerCommand.getWorkingDirectory(), CLONES_DIR))
            .directoryName(stealerRepoConfig.getDirectoryName())
            .build();
        final Vcs vcs = vcsFactory.newVcs(repositoryConfig);
        final Repository repository = Repository.builder()
            .repositoryConfig(repositoryConfig)
            .vcs(vcs)
            .build();
        repository.addStep(new SVCCloneStep());
        repository.addStep(new InitStep());
        stealerRepoConfig.getStealerStepConfigs().forEach(stealerStepConfig -> repository.addStep(newStep(stealerStepConfig)));
        repositories.add(repository);
    }

    private Step newStep(final StealerStepConfig stealerStepConfig) {
        final StepConfig stepConfig = StepConfig.builder()
            // TODO : needed mapping
            .build();
        return new DumbStep().setStepConfig(stepConfig);
    }

    @Deprecated // command should be passed
    public void steal() {
        repositories.forEach(repository -> repository.execute());
    }

    public void init() {
        initDirectories();
        initRepositoryConfigs();
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

    private void initRepositoryConfigs() {
        repositoryConfigs.forEach(repositoryConfig -> {
            try {
                final Repository repository = new Repository(repositoryConfig, repoTypeToVcs(repositoryConfig));
                repositories.add(addConfiguredSteps(repository));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private Repository addConfiguredSteps(final Repository repository) {
        repository.addStep(new SVCCloneStep());
        // TODO : add more steps
        return repository;
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
