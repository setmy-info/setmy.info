package info.setmy.exceptions;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class ExpiredException extends ForbiddenException {

    public ExpiredException() {
        super();
    }

    public ExpiredException(final String string) {
        super(string);
    }

    public ExpiredException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public ExpiredException(Throwable throwable) {
        super(throwable);
    }

    protected ExpiredException(String string, Throwable throwable, boolean bln, boolean bln1) {
        super(string, throwable, bln, bln1);
    }
}
