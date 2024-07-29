package info.setmy.vcs.exceptions;

import info.setmy.exceptions.UncheckedException;

/**
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class VcsException extends UncheckedException {

    public VcsException() {
        super();
    }

    public VcsException(final String string) {
        super(string);
    }

    public VcsException(final String string, final Throwable throwable) {
        super(string, throwable);
    }

    public VcsException(final Throwable throwable) {
        super(throwable);
    }

    protected VcsException(final String string, final Throwable throwable,
                           final boolean bln, final boolean bln1
    ) {
        super(string, throwable, bln, bln1);
    }
}
