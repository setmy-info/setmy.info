package info.setmy.exceptions;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class DoesNotExistException extends UncheckedException {

    public DoesNotExistException() {
        super();
    }

    public DoesNotExistException(final String string) {
        super(string);
    }

    public DoesNotExistException(final String string,
            final Throwable throwable) {
        super(string, throwable);
    }

    public DoesNotExistException(final Throwable throwable) {
        super(throwable);
    }

    protected DoesNotExistException(final String string,
            final Throwable throwable, final boolean bln, final boolean bln1) {
        super(string, throwable, bln, bln1);
    }
}
