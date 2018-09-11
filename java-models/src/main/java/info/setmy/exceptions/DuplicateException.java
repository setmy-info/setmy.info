package info.setmy.exceptions;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class DuplicateException extends UncheckedException {

    public DuplicateException() {
        super();
    }

    public DuplicateException(final String string) {
        super(string);
    }

    public DuplicateException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public DuplicateException(Throwable throwable) {
        super(throwable);
    }

    protected DuplicateException(String string, Throwable throwable, boolean bln, boolean bln1) {
        super(string, throwable, bln, bln1);
    }
}
