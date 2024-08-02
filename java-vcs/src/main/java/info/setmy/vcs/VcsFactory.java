package info.setmy.vcs;

import info.setmy.vcs.exceptions.VcsException;
import info.setmy.vcs.models.RepoType;
import info.setmy.vcs.models.RepositoryConfig;

public class VcsFactory {

    private final static VcsFactory INSTANCE = new VcsFactory();

    public static VcsFactory getInstance() {
        return INSTANCE;
    }

    public Vcs newVcs(final RepositoryConfig repositoryConfig) {
        final RepoType repoType = repositoryConfig.getRepoType();
        switch (repoType) {
            case GIT -> {
                return new GitVcs(repositoryConfig);
            }
            case HG -> {
                return new HgVcs(repositoryConfig);
            }
            default -> throw new VcsException("Not supported repo type " + repoType);
        }
    }
}
