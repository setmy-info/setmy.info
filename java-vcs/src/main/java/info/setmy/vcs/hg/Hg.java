package info.setmy.vcs.hg;

import info.setmy.vcs.BaseVcs;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class Hg extends BaseVcs {

    public Hg(final String url, final String workingDirectory) {
        super(url, workingDirectory);
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
