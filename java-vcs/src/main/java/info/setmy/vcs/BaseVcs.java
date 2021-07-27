package info.setmy.vcs;

import info.setmy.exec.Executor;
import info.setmy.vcs.exceptions.VcsException;
import java.io.File;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public abstract class BaseVcs {

    private final String url;
    private final File directory;

    public BaseVcs(final String url, final String workingDirectory) {
        this.url = url;
        this.directory = new File(workingDirectory);
    }

    public void doClone() {
        final Executor executor = newExecutor().setWorkingDirectory(directory);
        final String[] params = buildCloneCommand();
        executor.exec(params);
        executor.waitFor();
        validateCloneResult(transformCloneExistValue(executor.getExitValue()));
    }

    public void doCheckout(final String branchName) {
        final Executor executor = newExecutor().setWorkingDirectory(makeCheckoutWorkingDirectory(url));
        final String[] params = buildCheckoutCommand(branchName);
        executor.exec(params);
        executor.waitFor();
        validateCheckoutResult(transformCheckoutExistValue(executor.getExitValue()));
    }

    protected Executor newExecutor() {
        final Executor executor = new Executor()
                .setTimeout(0)
                .setBlocking(false);
        return executor;
    }

    protected String[] buildCloneCommand() {
        final String[] params = {getCommand(), getCloneSubCommand(), url};
        return params;
    }

    protected String[] buildCheckoutCommand(final String branchName) {
        final String[] params = {getCommand(), getCheckoutSubCommand(), branchName};
        return params;
    }

    protected abstract String getCommand();

    protected abstract String getCloneSubCommand();

    protected abstract String getCheckoutSubCommand();

    protected int transformCloneExistValue(final int exitValue) {
        return exitValue;
    }

    protected int transformCheckoutExistValue(final int exitValue) {
        return exitValue;
    }

    private void validateCloneResult(final int transformExistValue) {
        if (transformExistValue != 0) {
            throw new VcsException("Clone failed");
        }
    }

    private void validateCheckoutResult(final int transformExistValue) {
        if (transformExistValue != 0) {
            throw new VcsException("Checkout failed");
        }
    }

    private String makeCheckoutWorkingDirectory(final String url) {
        final String[] parts = url.split("/");
        final String lastPart = parts[parts.length - 1];
        final String[] name = lastPart.split("\\.");
        return directory.getAbsolutePath() + "/" + name[0];
    }
}
