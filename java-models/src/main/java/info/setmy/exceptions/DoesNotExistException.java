package info.setmy.exceptions;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class DoesNotExistException extends UncheckedException {

    public DoesNotExistException() {
        super();
    }

    public DoesNotExistException(final String string) {
        super(string);
    }

    public DoesNotExistException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public DoesNotExistException(Throwable throwable) {
        super(throwable);
    }

    protected DoesNotExistException(String string, Throwable throwable, boolean bln, boolean bln1) {
        super(string, throwable, bln, bln1);
    }
}
