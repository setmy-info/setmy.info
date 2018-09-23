package info.setmy.exceptions;

/**
 * Exception to roll back transactions.
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class ParsingException extends UncheckedException {

    public ParsingException() {
        super();
    }

    public ParsingException(final String string) {
        super(string);
    }

    public ParsingException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public ParsingException(Throwable throwable) {
        super(throwable);
    }

    protected ParsingException(String string, Throwable throwable, boolean bln, boolean bln1) {
        super(string, throwable, bln, bln1);
    }
}
