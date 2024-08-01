package info.setmy.exceptions.web;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class FailedDependencyWebException extends WebException {

    @Override
    public int getStatusCode() {
        return 424;
    }

    public FailedDependencyWebException(final String string) {
        super(string);
    }
}
