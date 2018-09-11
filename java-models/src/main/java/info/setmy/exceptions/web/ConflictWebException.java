package info.setmy.exceptions.web;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class ConflictWebException extends WebException {

    @Override
    public int getStatusCode() {
        return 409;
    }

    public ConflictWebException(final String string) {
        super(string);
    }
}
