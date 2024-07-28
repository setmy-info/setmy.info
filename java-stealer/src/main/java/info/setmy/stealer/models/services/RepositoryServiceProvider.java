package info.setmy.stealer.models.services;

import info.setmy.stealer.models.config.RepositoryConfig;
import info.setmy.vcs.Vcs;
import info.setmy.vcs.VcsFactory;

public class RepositoryServiceProvider {

    public RepositoryService getRepositoryService(final RepositoryConfig repositoryConfig) {
        validate(repositoryConfig);
        final Vcs vcs = VcsFactory.instanceOf(
            repositoryConfig.getRepoType(),
            repositoryConfig.getUrl(),
            repositoryConfig.getCloneDirectory(),
            repositoryConfig.getModuleName()
        );
        return new RepositoryService(repositoryConfig, vcs);
    }

    private void validate(final RepositoryConfig repositoryConfig) {
        // TODO : throw new StealerValidationException(); in case of non ready config etc
    }
}
