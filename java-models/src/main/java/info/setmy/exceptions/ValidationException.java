package info.setmy.exceptions;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class ValidationException extends UncheckedException {

    public ValidationException() {
        super();
    }

    public ValidationException(final String string) {
        super(string);
    }

    public ValidationException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public ValidationException(Throwable throwable) {
        super(throwable);
    }

    protected ValidationException(String string, Throwable throwable, boolean bln, boolean bln1) {
        super(string, throwable, bln, bln1);
    }
}
