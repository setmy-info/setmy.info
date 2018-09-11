package info.setmy.exceptions.web;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class UpgradeRequiredWebException extends WebException {

    @Override
    public int getStatusCode() {
        return 426;
    }

    public UpgradeRequiredWebException(final String string) {
        super(string);
    }
}
