package info.setmy.exceptions.web;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class RequestTimeoutWebException extends WebException {

    @Override
    public int getStatusCode() {
        return 408;
    }

    public RequestTimeoutWebException(final String string) {
        super(string);
    }
}
