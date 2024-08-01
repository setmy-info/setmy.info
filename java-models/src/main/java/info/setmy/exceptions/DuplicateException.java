package info.setmy.exceptions;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class DuplicateException extends UncheckedException {

    public DuplicateException() {
        super();
    }

    public DuplicateException(final String string) {
        super(string);
    }

    public DuplicateException(final String string, final Throwable throwable) {
        super(string, throwable);
    }

    public DuplicateException(final Throwable throwable) {
        super(throwable);
    }

    protected DuplicateException(final String string, final Throwable throwable, final boolean bln, final boolean bln1) {
        super(string, throwable, bln, bln1);
    }
}
