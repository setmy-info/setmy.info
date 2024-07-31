package info.setmy.stealer.services;

import info.setmy.stealer.exceptions.StealerException;
import info.setmy.stealer.models.StealerConfig;
import info.setmy.vcs.Vcs;
import info.setmy.vcs.VcsFactory;
import info.setmy.vcs.models.RepositoryConfig;
import lombok.Getter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.apache.commons.io.FileUtils.copyDirectory;

@Getter
public class StealerService {

    public static final String STEALER_DIR = ".stealer";
    public static final String CLONE_DIR = "clone";
    public static final String COPY_DIR = "copy";

    private final VcsFactory vcsFactory = VcsFactory.getInstance();

    private final StepConfigMapper stepConfigMapper = StepConfigMapper.getInstance();

    public void steal(final StealerConfig stealerConfig) {
        doSteal(init(stealerConfig));
    }

    private StealerInnerConfig init(final StealerConfig stealerConfig) {
        final File workingDirectory = stealerConfig.getWorkingDirectory();
        final File stealerDirectory = new File(workingDirectory, STEALER_DIR);
        final StealerInnerConfig stealerInnerConfig = createDirectories(
            StealerInnerConfig.builder()
                .workingDirectory(workingDirectory)
                .stealerDirectory(stealerDirectory)
                .cloneDirectory(new File(stealerDirectory, CLONE_DIR))
                .copyDirectory(new File(stealerDirectory, COPY_DIR))
                .stepsConfig(stepConfigMapper.toStepInnerConfigList(stealerConfig.getStepConfigs()))
                .build()
        );
        return stealerInnerConfig;
    }


    private StealerInnerConfig createDirectories(final StealerInnerConfig stealerInnerConfig) {
        stealerInnerConfig.getStealerDirectory().mkdirs();
        stealerInnerConfig.getCloneDirectory().mkdirs();
        stealerInnerConfig.getCopyDirectory().mkdirs();
        return stealerInnerConfig;
    }

    private void doSteal(final StealerInnerConfig stealerConfig) {
        doCloneAndCheckout(stealerConfig);
        doCopy(stealerConfig);
        doCleanup(stealerConfig);
        doPatch(stealerConfig);
        doChange(stealerConfig);
        doFinalization(stealerConfig);
    }

    private void doCloneAndCheckout(final StealerInnerConfig stealerInnerConfig) {
        stealerInnerConfig.getStepsConfig().forEach(stepConfig -> doStepCloneAndCheckout(stealerInnerConfig, stepConfig));
    }

    private void doStepCloneAndCheckout(final StealerInnerConfig stealerConfig, final StepInnerConfig stepInnerConfig) {
        final File cloneDir = stealerConfig.getCloneDirectory();
        final Vcs vcs = vcsFactory.newVcs(
            RepositoryConfig.builder()
                .repoType(stepInnerConfig.getRepoType())
                .url(stepInnerConfig.getUrl())
                .cloningDirectory(cloneDir)
                .directoryName(stepInnerConfig.getDirectoryName())
                .build());
        stepInnerConfig.toBuilder()
            .vcs(vcs)
            .build();
        vcs.doClone();
        stepInnerConfig.getOptionalBranchName().ifPresent(newBranchName -> vcs.doCheckout(newBranchName));
    }

    private void doCopy(final StealerInnerConfig stealerInnerConfig) {
        stealerInnerConfig.getStepsConfig().forEach(stepInnerConfig -> doStepCopy(stealerInnerConfig, stepInnerConfig));

    }

    private void doStepCopy(final StealerInnerConfig stealerInnerConfig, final StepInnerConfig stepInnerConfig) {
        onDemandKreateNonEmptyList(stepInnerConfig.getSubDirectories()).forEach(subDirectory -> doStepCopyForSubDirectory(stealerInnerConfig, stepInnerConfig, subDirectory));
    }

    private List<String> onDemandKreateNonEmptyList(final List<String> subDirectories) {
        if (subDirectories.isEmpty()) {
            return List.of("");
        }
        return subDirectories;
    }

    private void doStepCopyForSubDirectory(final StealerInnerConfig stealerInnerConfig, final StepInnerConfig stepInnerConfig, final String subDirectoryName) {
        final File cloneDir = new File(stealerInnerConfig.getCloneDirectory(), stepInnerConfig.getDirectoryName());
        final File copyDir = new File(stealerInnerConfig.getCopyDirectory(), stepInnerConfig.getDirectoryName());
        final File fromDir = new File(cloneDir, subDirectoryName);
        copyDir.mkdirs();
        try {
            copyDirectory(fromDir, copyDir);
        } catch (IOException e) {
            throw new StealerException(e);
        }

    }

    private void doCleanup(final StealerInnerConfig stealerInnerConfig) {
        stealerInnerConfig.getStepsConfig().forEach(stepInnerConfig -> doStepCleanup(stealerInnerConfig, stepInnerConfig));
    }

    private void doStepCleanup(final StealerInnerConfig stealerInnerConfig, final StepInnerConfig stepInnerConfig) {
        stepInnerConfig.getCleanup().forEach(cleanupCommand -> doStepCleanup(stealerInnerConfig, stepInnerConfig, cleanupCommand));
    }

    private void doStepCleanup(final StealerInnerConfig stealerInnerConfig, final StepInnerConfig stepInnerConfig, final String cleanupCommand) {
        final File removable = new File(new File(stealerInnerConfig.getCopyDirectory(), stepInnerConfig.getDirectoryName()), cleanupCommand);
        if (removable.isDirectory()) {
            try {
                FileUtils.deleteDirectory(removable);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (removable.isFile()) {
            removable.delete();
        }
    }

    private void doPatch(final StealerInnerConfig stealerInnerConfig) {
        stealerInnerConfig.getStepsConfig().forEach(stepInnerConfig -> doStepPatch(stealerInnerConfig, stepInnerConfig));
    }

    private void doStepPatch(final StealerInnerConfig stealerInnerConfig, final StepInnerConfig stepInnerConfig) {
    }

    private void doChange(final StealerInnerConfig stealerInnerConfig) {
        stealerInnerConfig.getStepsConfig().forEach(stepInnerConfig -> doStepChange(stealerInnerConfig, stepInnerConfig));
    }

    private void doStepChange(final StealerInnerConfig stealerInnerConfig, final StepInnerConfig stepInnerConfig) {
    }

    private void doFinalization(final StealerInnerConfig stealerInnerConfig) {
        stealerInnerConfig.getStepsConfig().forEach(stepInnerConfig -> doStepFinalization(stealerInnerConfig, stepInnerConfig));
    }

    private void doStepFinalization(final StealerInnerConfig stealerInnerConfig, final StepInnerConfig stepInnerConfig) {
    }
}
