package info.setmy.exceptions;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class LengthValidationException extends ValidationException {

    public LengthValidationException() {
        super();
    }

    public LengthValidationException(final String string) {
        super(string);
    }

    public LengthValidationException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public LengthValidationException(Throwable throwable) {
        super(throwable);
    }

    protected LengthValidationException(String string, Throwable throwable, boolean bln, boolean bln1) {
        super(string, throwable, bln, bln1);
    }
}
