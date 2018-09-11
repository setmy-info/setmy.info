package info.setmy.exceptions.web;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class NotFoundWebException extends WebException {

    @Override
    public int getStatusCode() {
        return 404;
    }

    public NotFoundWebException(final String string) {
        super(string);
    }
}
