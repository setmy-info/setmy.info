package info.setmy.models.storage;

import info.setmy.exceptions.UncheckedException;
import static info.setmy.models.storage.StorageValidator.instance;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public final class Storage {

    private final String directoryLocation;

    private File storageDirectory;

    public Storage(final String directoryLocation) {
        this.directoryLocation = StringUtils.trim(directoryLocation);
    }

    public void init() {
        if (directoryLocation == null) {
            throw new UncheckedException("Storage directory is not set");
        }
        instance().validateFileName(directoryLocation);
        final File file = new File(directoryLocation);
        if (!file.isDirectory()) {
            throw new UncheckedException(String.format("Storage location '%s' is not directory", directoryLocation));
        }
        storageDirectory = file;
    }

    public Optional<StorageFile> createStorageFile(final StoragePattern pattern) {
        final String directoryName = StringUtils.trim(pattern.toString());
        String fileName;
        if (pattern.getName() == null) {
            final UUID uuid = UUID.randomUUID();
            fileName = uuid.toString();
        } else {
            fileName = pattern.getName();
        }
        instance().validateFileName(fileName);
        final Optional<File> directory = newDirectory(directoryName);
        if (directory.isPresent()) { // dir is created
            final File directoryFile = directory.get();
            final File file = new File(directoryFile, fileName);
            try {
                if (file.createNewFile()) {
                    return Optional.of(new StorageFile(directoryFile, file, directoryName, fileName));
                }
            } catch (IOException ex) {
            }
        }
        return Optional.empty();
    }

    public File[] listStorageFiles(final String directoryPath) {
        final Optional<StorageFile> dir = getStorageDirectory(directoryPath);
        if (dir.isPresent()) {
            return dir.get().getParent().listFiles();
        }
        return new File[0];
    }

    public Optional<FileInputStream> getStorageFileStream(final String directoryPath) {
        try {
            return Optional.of(new FileInputStream(getStorageFile(directoryPath).get().getChild()));
        } catch (FileNotFoundException ex) {
            return Optional.empty();
        }
    }

    public Optional<StorageFile> getStorageFile(final String fileNamePath) {
        final File file = getValidatedFileObject(StringUtils.trim(fileNamePath));
        if (file.exists() && file.isFile()) {
            return Optional.of(new StorageFile(null, file, null, fileNamePath));
        }
        return Optional.empty();
    }

    public Optional<StorageFile> getStorageDirectory(final String directoryPath) {
        final File directory = getValidatedFileObject(StringUtils.trim(directoryPath));
        if (directory.exists() && directory.isDirectory()) {
            return Optional.of(new StorageFile(directory, null, directoryPath, null));
        }
        return Optional.empty();
    }

    private Optional<File> newDirectory(final String directoryName) {
        instance().validateFileName(directoryName);
        final String dirFullPath = getPath(directoryName);
        final File directory = new File(dirFullPath);
        if (directory.exists()) {
            return Optional.of(directory);
        } else {
            boolean result = directory.mkdirs();
            if (result) {
                return Optional.of(directory);
            }
        }
        return Optional.empty();
    }

    private File getValidatedFileObject(final String directoryPath) {
        instance().validateUperAndHomeChange(directoryPath);
        final File file = new File(storageDirectory, directoryPath);
        return file;
    }

    private String getPath(final String directoryPath) {
        return directoryLocation + "/" + directoryPath;
    }
}
