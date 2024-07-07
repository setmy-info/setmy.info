package info.setmy.models.storage;

import info.setmy.exceptions.UncheckedException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.StringJoiner;

import static java.util.Optional.empty;
import static org.apache.commons.lang3.StringUtils.trim;

/**
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public final class Storage {

    private final String directoryLocation;

    private final StorageValidator storageValidator = DefaultStorageValidator.instance();

    private File storageDirectory;

    public Storage(final String directoryLocation) {
        this.directoryLocation = trim(directoryLocation);
    }

    public Storage init() {
        if (directoryLocation == null) {
            throw new UncheckedException("Storage directory is not set");
        }
        storageValidator.validateFileName(directoryLocation);
        final File file = new File(directoryLocation);
        if (!file.isDirectory()) {
            throw new UncheckedException(String.format("Storage location '%s' is not directory", directoryLocation));
        }
        storageDirectory = file;
        return this;
    }

    public Optional<StorageFile> createStorageFile(final Object pattern) {
        return createStorageFile(pattern.toString());
    }

    public Optional<StorageFile> createStorageFile(final String path) {
        final String fullPathWithFile = trim(path);
        final String[] fullPathPieces = toPieces(fullPathWithFile);
        if (fullPathPieces.length == 0) {
            return empty();
        }
        final String fileName = fullPathPieces[fullPathPieces.length - 1];
        final String directoryName = makeDirName(fullPathPieces);
        storageValidator.validateFileName(fileName);
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
        return empty();
    }

    private String makeDirName(final String[] fullPathPieces) {
        final String[] pathOnly = Arrays.copyOf(fullPathPieces, fullPathPieces.length - 1);
        final StringJoiner joiner = new StringJoiner("/");
        Arrays.stream(pathOnly).forEach(element -> joiner.add(trim(element)));
        return joiner.toString();
    }

    private String[] toPieces(final String string) {
        return string.split("[\\\\/]");
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
            return empty();
        }
    }

    public Optional<StorageFile> getStorageFile(final String fileNamePath) {
        final File file = getValidatedFileObject(trim(fileNamePath));
        if (file.exists() && file.isFile()) {
            return Optional.of(new StorageFile(null, file, null, fileNamePath));
        }
        return empty();
    }

    public Optional<StorageFile> getStorageDirectory(final String directoryPath) {
        final var directory = getValidatedFileObject(trim(directoryPath));
        if (directory.exists() && directory.isDirectory()) {
            return Optional.of(new StorageFile(directory, null, directoryPath, null));
        }
        return empty();
    }

    private Optional<File> newDirectory(final String directoryName) {
        storageValidator.validateFileName(directoryName);
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
        return empty();
    }

    private File getValidatedFileObject(final String directoryPath) {
        storageValidator.validateUperAndHomeChange(directoryPath);
        final File file = new File(storageDirectory, directoryPath);
        return file;
    }

    private String getPath(final String directoryPath) {
        return directoryLocation + "/" + directoryPath;
    }
}
