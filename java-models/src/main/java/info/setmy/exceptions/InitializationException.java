package info.setmy.exceptions;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class InitializationException extends ForbiddenException {

    public InitializationException() {
        super();
    }

    public InitializationException(final String string) {
        super(string);
    }

    public InitializationException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public InitializationException(Throwable throwable) {
        super(throwable);
    }

    protected InitializationException(String string, Throwable throwable, boolean bln, boolean bln1) {
        super(string, throwable, bln, bln1);
    }
}
