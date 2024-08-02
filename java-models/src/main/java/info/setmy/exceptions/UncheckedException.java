package info.setmy.exceptions;

/**
 * Root exception.
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class UncheckedException extends RuntimeException {

    public UncheckedException() {
        super();
    }

    public UncheckedException(final String string) {
        super(string);
    }

    public UncheckedException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public UncheckedException(Throwable throwable) {
        super(throwable);
    }

    protected UncheckedException(String string, Throwable throwable, boolean bln, boolean bln1) {
        super(string, throwable, bln, bln1);
    }
}
