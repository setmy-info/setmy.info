package info.setmy.exceptions.web;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class LockedWebException extends WebException {

    @Override
    public int getStatusCode() {
        return 423;
    }

    public LockedWebException(final String string) {
        super(string);
    }
}
