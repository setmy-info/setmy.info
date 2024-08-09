package info.setmy.exceptions.web;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class UnsupportedMediaTypeWebException extends WebException {

    @Override
    public int getStatusCode() {
        return 415;
    }

    public UnsupportedMediaTypeWebException(final String string) {
        super(string);
    }
}
