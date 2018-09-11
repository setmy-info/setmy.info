package info.setmy.exceptions.web;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class BadRequestWebException extends WebException {

    @Override
    public int getStatusCode() {
        return 400;
    }

    public BadRequestWebException(final String string) {
        super(string);
    }
}
