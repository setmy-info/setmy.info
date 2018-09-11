package info.setmy.exceptions.web;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class RequestHeaderFieldsTooLargeWebException extends WebException {

    @Override
    public int getStatusCode() {
        return 431;
    }

    public RequestHeaderFieldsTooLargeWebException(final String string) {
        super(string);
    }
}
