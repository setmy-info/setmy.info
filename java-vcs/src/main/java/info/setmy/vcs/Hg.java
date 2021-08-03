package info.setmy.vcs;

/**
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class Hg extends Vcs {

    Hg(final String url, final String cloneWorkingDirectory) {
        super(url, cloneWorkingDirectory);
    }

    Hg(final String url, final String cloneWorkingDirectory, final String moduleName) {
        super(url, cloneWorkingDirectory, moduleName);
    }

    @Override
    public String getCommand() {
        return "hg";
    }

    @Override
    public String getCloneSubCommand() {
        return "clone";
    }

    @Override
    public String getCheckoutSubCommand() {
        return "update";
    }
}
