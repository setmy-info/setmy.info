package info.setmy.vcs;

import info.setmy.vcs.exceptions.VcsException;
import info.setmy.vcs.models.CloningConfig;
import info.setmy.vcs.models.RepoType;

public class VcsFactory {

    private final static VcsFactory INSTANCE = new VcsFactory();

    public static VcsFactory getInstance() {
        return INSTANCE;
    }

    public Vcs newVcs(final CloningConfig cloningConfig) {
        final RepoType repoType = cloningConfig.getRepoType();
        switch (repoType) {
            case GIT -> {
                return new GitVcs(cloningConfig);
            }
            case HG -> {
                return new HgVcs(cloningConfig);
            }
            default -> throw new VcsException("Not supported repo type " + repoType);
        }
    }
}
