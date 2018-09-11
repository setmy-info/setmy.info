package info.setmy.exceptions.web;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class PayloadTooLargeWebException extends WebException {

    @Override
    public int getStatusCode() {
        return 413;
    }

    public PayloadTooLargeWebException(final String string) {
        super(string);
    }
}
