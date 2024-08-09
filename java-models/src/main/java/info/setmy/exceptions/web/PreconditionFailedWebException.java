package info.setmy.exceptions.web;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class PreconditionFailedWebException extends WebException {

    @Override
    public int getStatusCode() {
        return 412;
    }

    public PreconditionFailedWebException(final String string) {
        super(string);
    }
}
