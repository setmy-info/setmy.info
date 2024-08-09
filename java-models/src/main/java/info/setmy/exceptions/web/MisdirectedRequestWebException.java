package info.setmy.exceptions.web;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class MisdirectedRequestWebException extends WebException {

    @Override
    public int getStatusCode() {
        return 421;
    }

    public MisdirectedRequestWebException(final String string) {
        super(string);
    }
}
