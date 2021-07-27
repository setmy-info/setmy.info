package info.setmy.exec.exceptions;

import info.setmy.exceptions.UncheckedException;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class ExecutionError extends UncheckedException {

    public ExecutionError() {
        super();
    }

    public ExecutionError(final String string) {
        super(string);
    }

    public ExecutionError(final String string, final Throwable throwable) {
        super(string, throwable);
    }

    public ExecutionError(final Throwable throwable) {
        super(throwable);
    }

    protected ExecutionError(final String string, final Throwable throwable,
            final boolean bln, final boolean bln1
    ) {
        super(string, throwable, bln, bln1);
    }
}
