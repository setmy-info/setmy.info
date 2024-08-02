package info.setmy.exceptions.web;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class URITooLongWebException extends WebException {

    @Override
    public int getStatusCode() {
        return 414;
    }

    public URITooLongWebException(final String string) {
        super(string);
    }
}
