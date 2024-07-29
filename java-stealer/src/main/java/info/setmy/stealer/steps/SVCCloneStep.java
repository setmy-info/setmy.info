package info.setmy.stealer.steps;

import info.setmy.stealer.exceptions.StealerException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class SVCCloneStep extends BaseStep implements Step {

    @Override
    public void execute() {
        try {
            makeCloneDir();
        } catch (IOException e) {
            throw new StealerException(e);
        }
        repository.getVcs().doClone();
    }

    private void makeCloneDir() throws IOException {
        final File clonedDirectory = repository.getRepositoryConfig().getClonedDirectory();
        Files.createDirectories(clonedDirectory.toPath());
    }
}
