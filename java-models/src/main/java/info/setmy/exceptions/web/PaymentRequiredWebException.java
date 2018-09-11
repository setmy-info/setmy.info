package info.setmy.exceptions.web;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class PaymentRequiredWebException extends WebException {

    @Override
    public int getStatusCode() {
        return 402;
    }

    public PaymentRequiredWebException(final String string) {
        super(string);
    }
}
