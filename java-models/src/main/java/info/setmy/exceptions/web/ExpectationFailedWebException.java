package info.setmy.exceptions.web;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class ExpectationFailedWebException extends WebException {

    @Override
    public int getStatusCode() {
        return 417;
    }

    public ExpectationFailedWebException(final String string) {
        super(string);
    }
}
