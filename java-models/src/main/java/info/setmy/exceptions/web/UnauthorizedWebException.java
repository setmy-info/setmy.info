package info.setmy.exceptions.web;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class UnauthorizedWebException extends WebException {

    @Override
    public int getStatusCode() {
        return 401;
    }

    public UnauthorizedWebException(final String string) {
        super(string);
    }
}
