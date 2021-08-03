package info.setmy.vcs;

import info.setmy.models.storage.StorageValidator;
import info.setmy.vcs.exceptions.VcsException;

/**
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public final class VcsFactory {

    public static final Vcs instanceOf(final RepoType repoType, final String url, final String cloneWorkingDirectory) {
        switch (repoType) {
            case GIT -> {
                return new Git(url, StorageValidator.instance().validateAgainstDirChanges(cloneWorkingDirectory));
            }
            case HG -> {
                return new Hg(url, StorageValidator.instance().validateAgainstDirChanges(cloneWorkingDirectory));
            }
            default ->
                throw new VcsException("Not supported repo type " + repoType);
        }
    }

    public static final Vcs instanceOf(final RepoType repoType, final String url, final String cloneWorkingDirectory, final String moduleName) {
        switch (repoType) {
            case GIT -> {
                return new Git(url, StorageValidator.instance().validateAgainstDirChanges(cloneWorkingDirectory), StorageValidator.instance().validateAgainstDirChanges(moduleName));
            }
            case HG -> {
                return new Hg(url, StorageValidator.instance().validateAgainstDirChanges(cloneWorkingDirectory), StorageValidator.instance().validateAgainstDirChanges(moduleName));
            }
            default ->
                throw new VcsException("Not supported repo type " + repoType);
        }
    }
}
