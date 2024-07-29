package info.setmy.stealer;

import info.setmy.stealer.models.config.StealerCommand;
import info.setmy.stealer.models.config.StealerRepoConfig;
import info.setmy.stealer.models.config.StealerStepConfig;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import static info.setmy.vcs.models.RepoType.GIT;

/**
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class StealerIT {

    private final String WORKING_DIR = "./test-data/" + this.getClass().getSimpleName();

    private Stealer stealer;

    StealerCommand command;

    @BeforeEach
    public void before() throws IOException {
        stealer = new Stealer(null, WORKING_DIR);
        FileUtils.deleteDirectory(new File(WORKING_DIR));
        Files.createDirectories(Path.of(WORKING_DIR));
    }

    @Test
    public void getClonesDir() throws MalformedURLException {
        final StealerStepConfig initStepConfig = StealerStepConfig.builder()
            .build();
        final StealerStepConfig cleanStepConfig = StealerStepConfig.builder()
            .build();
        final StealerStepConfig replaceStepConfig = StealerStepConfig.builder()
            .build();
        final StealerRepoConfig stealerRepoConfig = StealerRepoConfig.builder()
            .repoType(GIT)
            .url(new URL("https://github.com/setmy-info/stealer-test-a.git"))
            .directoryName("stealer-test-a")
            .build();
        stealerRepoConfig.getStealerStepConfigs().add(initStepConfig);
        stealerRepoConfig.getStealerStepConfigs().add(cleanStepConfig);
        stealerRepoConfig.getStealerStepConfigs().add(replaceStepConfig);
        command = StealerCommand.builder()
            .workingDirectory(WORKING_DIR)
            .build();
        command.getStealerRepoConfigs().add(stealerRepoConfig);

        stealer.steal(command);
    }
}
