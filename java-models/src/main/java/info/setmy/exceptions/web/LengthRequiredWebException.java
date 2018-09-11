package info.setmy.exceptions.web;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class LengthRequiredWebException extends WebException {

    @Override
    public int getStatusCode() {
        return 411;
    }

    public LengthRequiredWebException(final String string) {
        super(string);
    }
}
