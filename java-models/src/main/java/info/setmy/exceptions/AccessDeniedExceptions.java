package info.setmy.exceptions;

/**
 * Exception to roll back transactions.
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class AccessDeniedExceptions extends UncheckedException {

    public AccessDeniedExceptions() {
        super();
    }

    public AccessDeniedExceptions(final String string) {
        super(string);
    }

    public AccessDeniedExceptions(final String string,
            final Throwable throwable) {
        super(string, throwable);
    }

    public AccessDeniedExceptions(final Throwable throwable) {
        super(throwable);
    }

    protected AccessDeniedExceptions(final String string,
            final Throwable throwable, final boolean bln, final boolean bln1) {
        super(string, throwable, bln, bln1);
    }
}
