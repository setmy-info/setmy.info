package info.setmy.exceptions.web;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class GoneWebException extends WebException {

    @Override
    public int getStatusCode() {
        return 410;
    }

    public GoneWebException(final String string) {
        super(string);
    }
}
