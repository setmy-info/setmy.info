package info.setmy.exceptions.web;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class TooManyRequestsWebException extends WebException {

    @Override
    public int getStatusCode() {
        return 429;
    }

    public TooManyRequestsWebException(final String string) {
        super(string);
    }
}
