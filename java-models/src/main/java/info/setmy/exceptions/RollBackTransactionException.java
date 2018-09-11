package info.setmy.exceptions;

/**
 * Exception to roll back transactions.
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class RollBackTransactionException extends UncheckedException {

    public RollBackTransactionException() {
        super();
    }

    public RollBackTransactionException(final String string) {
        super(string);
    }

    public RollBackTransactionException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public RollBackTransactionException(Throwable throwable) {
        super(throwable);
    }

    protected RollBackTransactionException(String string, Throwable throwable, boolean bln, boolean bln1) {
        super(string, throwable, bln, bln1);
    }
}
