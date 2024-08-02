package info.setmy.stealer.exceptions;

/**
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class StealerValidationException extends StealerException {

    public StealerValidationException() {
        super();
    }

    public StealerValidationException(final String string) {
        super(string);
    }

    public StealerValidationException(final String string, final Throwable throwable) {
        super(string, throwable);
    }

    public StealerValidationException(final Throwable throwable) {
        super(throwable);
    }

    protected StealerValidationException(final String string, final Throwable throwable,
                                         final boolean bln, final boolean bln1
    ) {
        super(string, throwable, bln, bln1);
    }
}
