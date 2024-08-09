package info.setmy.vcs;

import info.setmy.exec.Executor;
import info.setmy.vcs.exceptions.VcsValidationException;
import info.setmy.vcs.models.CommandData;
import info.setmy.vcs.models.RepositoryConfig;

import static org.apache.commons.lang3.StringUtils.isBlank;

public abstract class VcsBase implements Vcs {

    public abstract CommandData getCommandData();

    public abstract RepositoryConfig getRepositoryConfig();

    @Override
    public void doClone() {
        final Executor executor = newExecutor().setWorkingDirectory(fixDirectoryName(getRepositoryConfig()).getCloningDirectory());
        final String[] params = buildCloneCommand();
        executor.exec(params);
        executor.waitFor();
        validateCheckoutResult(executor.getExitValue());
    }

    private RepositoryConfig fixDirectoryName(final RepositoryConfig repositoryConfig) {
        if (isBlank(repositoryConfig.getDirectoryName())) {
            final String path = repositoryConfig.getUrl().getPath();
            final String[] parts = path.split("/");
            final String lastPart = parts[parts.length - 1];
            final String[] name = lastPart.split("\\.");
            final String newDirectoryName = name[0];
            return repositoryConfig.toBuilder()
                .directoryName(newDirectoryName)
                .build();
        }
        return repositoryConfig;
    }

    @Override
    public void doCheckout(final String branchName) {
        final Executor executor = newExecutor().setWorkingDirectory(fixDirectoryName(getRepositoryConfig()).getClonedDirectory());
        final String[] params = getCommandData().buildCheckoutCommand(branchName);
        executor.exec(params);
        executor.waitFor();
        validateCheckoutResult(executor.getExitValue());
    }

    @Override
    public void doFetch() {
        final Executor executor = newExecutor().setWorkingDirectory(fixDirectoryName(getRepositoryConfig()).getClonedDirectory());
        final String[] params = getCommandData().buildFetchCommand();
        executor.exec(params);
        executor.waitFor();
        validateCheckoutResult(executor.getExitValue());
    }

    @Override
    public void doPull() {
        final Executor executor = newExecutor().setWorkingDirectory(fixDirectoryName(getRepositoryConfig()).getClonedDirectory());
        final String[] params = getCommandData().buildPullCommand();
        executor.exec(params);
        executor.waitFor();
        validateCheckoutResult(executor.getExitValue());
    }

    @Override
    public void doPush() {
        final Executor executor = newExecutor().setWorkingDirectory(fixDirectoryName(getRepositoryConfig()).getClonedDirectory());
        final String[] params = getCommandData().buildPushCommand();
        executor.exec(params);
        executor.waitFor();
        validateCheckoutResult(executor.getExitValue());
    }

    protected Executor newExecutor() {
        final Executor executor = new Executor()
            .setTimeout(0)
            .setBlocking(false);
        return executor;
    }

    protected String[] buildCloneCommand() {
        final RepositoryConfig conf = fixDirectoryName(getRepositoryConfig());
        final String[] params = {
            getCommandData().getCommand(),
            getCommandData().getCloneSubCommand(),
            conf.getUrl().toString(),
            conf.getDirectoryName()
        };
        return params;
    }

    private void validateCheckoutResult(final int transformExistValue) {
        if (transformExistValue != 0) {
            throw new VcsValidationException("Checkout failed");
        }
    }
}
