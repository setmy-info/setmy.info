package info.setmy.crypto.exceptions;

import info.setmy.exceptions.UncheckedException;

/**
 * Root exception for all cryptographic operation failures.
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class CryptoException extends UncheckedException {

    public CryptoException(final String message) {
        super(message);
    }

    public CryptoException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CryptoException(final Throwable cause) {
        super(cause);
    }
}
