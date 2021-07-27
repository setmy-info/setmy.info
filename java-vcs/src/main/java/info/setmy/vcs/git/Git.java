package info.setmy.vcs.git;

import info.setmy.vcs.BaseVcs;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class Git extends BaseVcs {

    public Git(final String url, final String workingDirectory) {
        super(url, workingDirectory);
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
