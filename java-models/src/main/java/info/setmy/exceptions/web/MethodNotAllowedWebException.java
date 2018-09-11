package info.setmy.exceptions.web;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class MethodNotAllowedWebException extends WebException {

    @Override
    public int getStatusCode() {
        return 405;
    }

    public MethodNotAllowedWebException(final String string) {
        super(string);
    }
}
