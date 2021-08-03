package info.setmy.models.storage;

import info.setmy.exceptions.ForbiddenException;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public final class StorageValidator {

    private final static StorageValidator storageValidator = new StorageValidator();

    public static final StorageValidator instance() {
        return storageValidator;
    }

    public final String validateFileName(final String fileName) {
        return validateAgainstDirChanges(validateUperAndHomeChange(validateRootChange(fileName)));
    }

    private String validateRootChange(final String fileName) {
        if (fileName.startsWith("/")) {
            throw new ForbiddenException(format(fileName));
        }
        return fileName;
    }

    public final String validateUperAndHomeChange(final String fileName) {
        validateUperChange(fileName);
        validateHomeChange(fileName);// TODO : is it needed?
        return fileName;
    }

    private String validateUperChange(final String fileName) {
        if (-1 != fileName.indexOf("..")) {
            throw new ForbiddenException(format(fileName));
        }
        return fileName;
    }

    private String validateHomeChange(final String fileName) {
        if (-1 != fileName.indexOf("~")) {
            throw new ForbiddenException(format(fileName));
        }
        return fileName;
    }

    public final String validateAgainstDirChanges(final String fileName) {
        return validateRootChange2(validateCurrentChange(fileName));
    }

    private String validateCurrentChange(final String fileName) {
        if (fileName.contains("./") || fileName.contains("/./") || fileName.endsWith("./") || fileName.endsWith("/.") || fileName.startsWith("./")) {
            throw new ForbiddenException(format(fileName));
        }
        return fileName;
    }

    private String validateRootChange2(final String fileName) {
        // TODO : merge with previous root change validations
        if (fileName.contains("../") || fileName.contains("/../") || fileName.endsWith("../") || fileName.endsWith("/..") || fileName.startsWith("../")) {
            throw new ForbiddenException(format(fileName));
        }
        return fileName;
    }

    private static String format(final String fileName) {
        return String.format("File or directory '%s' is not allowed", fileName);
    }
}
