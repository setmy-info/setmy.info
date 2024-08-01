package info.setmy.jwt.exceptions;

import info.setmy.exceptions.UncheckedException;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class NotParsedException extends UncheckedException {

    public NotParsedException() {
        super();
    }

    public NotParsedException(final String string) {
        super(string);
    }

    public NotParsedException(final String string, final Exception ex) {
        super(string, ex);
    }
}
