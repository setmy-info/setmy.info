package info.setmy.exceptions.web;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class NotAcceptableWebException extends WebException {

    @Override
    public int getStatusCode() {
        return 406;
    }

    public NotAcceptableWebException(final String string) {
        super(string);
    }
}
