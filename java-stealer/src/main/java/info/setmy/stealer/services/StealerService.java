package info.setmy.stealer.services;

import info.setmy.stealer.models.StealerConfig;
import lombok.Getter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

@Getter
public class StealerService {

    public static final String STEALER_DIR = "stealer";
    public static final String CLONE_DIR = "stealer";
    public static final String COPY_DIR = "stealer";

    public void steal(final StealerConfig stealerConfig) {
        init(stealerConfig);
    }

    private static void init(final StealerConfig stealerConfig) {
        final File workingDirectory = stealerConfig.getWorkingDirectory();
        final File stealerDirectory = new File(workingDirectory, STEALER_DIR);
        final File cloneDirectory = new File(stealerDirectory, CLONE_DIR);
        final File copyDirectory = new File(stealerDirectory, COPY_DIR);
        createDirectory(stealerConfig.getWorkingDirectory());
        createDirectory(stealerDirectory);
    }

    private static void createDirectory(File workingDirectory) {
        try {
            FileUtils.createParentDirectories(workingDirectory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
