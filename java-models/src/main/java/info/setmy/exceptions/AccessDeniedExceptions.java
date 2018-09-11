package info.setmy.exceptions;

/**
 * Exception to roll back transactions.
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class AccessDeniedExceptions extends UncheckedException {

    public AccessDeniedExceptions() {
        super();
    }

    public AccessDeniedExceptions(final String string) {
        super(string);
    }

    public AccessDeniedExceptions(String string, Throwable throwable) {
        super(string, throwable);
    }

    public AccessDeniedExceptions(Throwable throwable) {
        super(throwable);
    }

    protected AccessDeniedExceptions(String string, Throwable throwable, boolean bln, boolean bln1) {
        super(string, throwable, bln, bln1);
    }
}
