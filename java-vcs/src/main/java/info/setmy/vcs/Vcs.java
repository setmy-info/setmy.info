package info.setmy.vcs;

import info.setmy.exec.Executor;
import info.setmy.vcs.exceptions.VcsException;

import java.io.File;

/**
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public abstract class Vcs {

    private final String url;
    private final File cloneWorkingDirectory;
    private final File workingDirectory;
    private String moduleName;

    protected Vcs(final String url, final String cloneWorkingDirectory) {
        this.url = url;
        this.cloneWorkingDirectory = new File(cloneWorkingDirectory);
        this.workingDirectory = new File(makeWorkingDirectoryAndSetModuleName(url));
    }

    protected Vcs(final String url, final String cloneWorkingDirectory, final String moduleName) {
        this.url = url;
        this.cloneWorkingDirectory = new File(cloneWorkingDirectory);
        this.moduleName = moduleName;
        this.workingDirectory = new File(this.cloneWorkingDirectory.getAbsolutePath() + "/" + this.moduleName);
    }

    public void doClone() {
        final Executor executor = newExecutor().setWorkingDirectory(cloneWorkingDirectory);
        final String[] params = buildCloneCommand();
        executor.exec(params);
        executor.waitFor();
        validateCloneResult(transformCloneExistValue(executor.getExitValue()));
    }

    public void doCheckout(final String branchName) {
        final Executor executor = newExecutor().setWorkingDirectory(workingDirectory);
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
        final String[] params = {getCommand(), getCloneSubCommand(), url, moduleName};
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

    private String makeWorkingDirectoryAndSetModuleName(final String url) {
        // repo module name
        final String[] parts = url.split("/");
        final String lastPart = parts[parts.length - 1];
        final String[] name = lastPart.split("\\.");
        this.moduleName = name[0];
        return cloneWorkingDirectory.getAbsolutePath() + "/" + this.moduleName;
    }

    public File getCloneWorkingDirectory() {
        return cloneWorkingDirectory;
    }

    public File getWorkingDirectory() {
        return workingDirectory;
    }
}
