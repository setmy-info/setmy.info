package info.setmy.vcs.exceptions;

/**
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class VcsValidationException extends VcsException {

    public VcsValidationException() {
        super();
    }

    public VcsValidationException(final String string) {
        super(string);
    }

    public VcsValidationException(final String string, final Throwable throwable) {
        super(string, throwable);
    }

    public VcsValidationException(final Throwable throwable) {
        super(throwable);
    }

    protected VcsValidationException(final String string, final Throwable throwable,
                                     final boolean bln, final boolean bln1
    ) {
        super(string, throwable, bln, bln1);
    }
}
