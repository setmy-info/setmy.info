package info.setmy.exceptions.web;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class PreconditionRequiredWebException extends WebException {

    @Override
    public int getStatusCode() {
        return 428;
    }

    public PreconditionRequiredWebException(final String string) {
        super(string);
    }
}
