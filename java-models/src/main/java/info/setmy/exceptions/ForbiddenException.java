package info.setmy.exceptions;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class ForbiddenException extends UncheckedException {

    public ForbiddenException() {
        super();
    }

    public ForbiddenException(final String string) {
        super(string);
    }

    public ForbiddenException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public ForbiddenException(Throwable throwable) {
        super(throwable);
    }

    protected ForbiddenException(String string, Throwable throwable, boolean bln, boolean bln1) {
        super(string, throwable, bln, bln1);
    }
}
