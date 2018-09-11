package info.setmy.exceptions.web;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class UnavailableForLegalReasonsWebException extends WebException {

    @Override
    public int getStatusCode() {
        return 451;
    }

    public UnavailableForLegalReasonsWebException(final String string) {
        super(string);
    }
}
