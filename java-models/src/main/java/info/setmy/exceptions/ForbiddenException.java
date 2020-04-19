package info.setmy.exceptions;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class ForbiddenException extends UncheckedException {

    public ForbiddenException() {
        super();
    }

    public ForbiddenException(final String string) {
        super(string);
    }

    public ForbiddenException(final String string, final Throwable throwable) {
        super(string, throwable);
    }

    public ForbiddenException(final Throwable throwable) {
        super(throwable);
    }

    protected ForbiddenException(final String string, final Throwable throwable, final boolean bln, final boolean bln1) {
        super(string, throwable, bln, bln1);
    }
}
