package info.setmy.stealer.exceptions;

import info.setmy.exceptions.UncheckedException;

/**
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class StealerException extends UncheckedException {

    public StealerException() {
        super();
    }

    public StealerException(final String string) {
        super(string);
    }

    public StealerException(final String string, final Throwable throwable) {
        super(string, throwable);
    }

    public StealerException(final Throwable throwable) {
        super(throwable);
    }

    protected StealerException(final String string, final Throwable throwable,
                               final boolean bln, final boolean bln1
    ) {
        super(string, throwable, bln, bln1);
    }
}
