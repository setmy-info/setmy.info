package info.setmy.exceptions.web;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class RangeNotSatisfiableWebException extends WebException {

    @Override
    public int getStatusCode() {
        return 416;
    }

    public RangeNotSatisfiableWebException(final String string) {
        super(string);
    }
}
