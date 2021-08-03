package info.setmy.vcs;

/**
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class Git extends Vcs {

    Git(final String url, final String cloneWorkingDirectory) {
        super(url, cloneWorkingDirectory);
    }

    Git(final String url, final String cloneWorkingDirectory, final String moduleName) {
        super(url, cloneWorkingDirectory, moduleName);
    }

    @Override
    public String getCommand() {
        return "git";
    }

    @Override
    public String getCloneSubCommand() {
        return "clone";
    }

    @Override
    public String getCheckoutSubCommand() {
        return "checkout";
    }
}
