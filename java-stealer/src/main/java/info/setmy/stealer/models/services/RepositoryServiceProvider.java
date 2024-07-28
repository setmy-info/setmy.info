package info.setmy.stealer.models.services;

import info.setmy.vcs.Vcs;
import info.setmy.vcs.VcsFactory;
import info.setmy.vcs.models.CloningConfig;

public class RepositoryServiceProvider {

    private final VcsFactory vcsFactory = VcsFactory.getInstance();

    public RepositoryService getRepositoryService(final CloningConfig cloningConfig) {
        validate(cloningConfig);
        final Vcs vcs = vcsFactory.newVcs(cloningConfig);
        return new RepositoryService(cloningConfig, vcs);
    }

    private void validate(final CloningConfig cloningConfig) {
        // TODO : throw new StealerValidationException(); in case of non ready config etc
    }
}
