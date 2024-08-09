package info.setmy.exceptions;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class ExpiredException extends ForbiddenException {

    public ExpiredException() {
        super();
    }

    public ExpiredException(final String string) {
        super(string);
    }

    public ExpiredException(final String string, final Throwable throwable) {
        super(string, throwable);
    }

    public ExpiredException(final Throwable throwable) {
        super(throwable);
    }

    protected ExpiredException(final String string, final Throwable throwable, final boolean bln, final boolean bln1) {
        super(string, throwable, bln, bln1);
    }
}
