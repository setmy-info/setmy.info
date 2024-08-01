package info.setmy.exceptions.web;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class ProxyAuthenticationRequiredWebException extends WebException {

    @Override
    public int getStatusCode() {
        return 407;
    }

    public ProxyAuthenticationRequiredWebException(final String string) {
        super(string);
    }
}
