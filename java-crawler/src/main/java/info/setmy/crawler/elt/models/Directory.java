package info.setmy.crawler.elt.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.File;

@Getter
//@SuperBuilder(toBuilder = true)
@RequiredArgsConstructor
public abstract class Directory {

    protected final File directory;

    protected File newFile(final String name) {
        return new File(directory, name);
    }

    protected File validate(final File file) {
        if (existingAsNonDirectory(file)) {
            throw new RuntimeException("Input directory exists and is not a directory: " + file.getAbsolutePath());
        }
        return file;
    }

    protected boolean existingAsNonDirectory(final File file) {
        return file.exists() && !file.isDirectory();
    }

    protected File create(final File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }
}
