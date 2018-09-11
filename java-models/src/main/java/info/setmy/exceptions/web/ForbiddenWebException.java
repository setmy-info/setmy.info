package info.setmy.exceptions.web;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class ForbiddenWebException extends WebException {

    @Override
    public int getStatusCode() {
        return 403;
    }

    public ForbiddenWebException(final String string) {
        super(string);
    }
}
