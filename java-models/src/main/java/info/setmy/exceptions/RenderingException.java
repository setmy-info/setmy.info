package info.setmy.exceptions;

/**
 * Exception to roll back transactions.
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class RenderingException extends UncheckedException {

    public RenderingException() {
        super();
    }

    public RenderingException(final String string) {
        super(string);
    }

    public RenderingException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public RenderingException(Throwable throwable) {
        super(throwable);
    }

    protected RenderingException(String string, Throwable throwable, boolean bln, boolean bln1) {
        super(string, throwable, bln, bln1);
    }
}
