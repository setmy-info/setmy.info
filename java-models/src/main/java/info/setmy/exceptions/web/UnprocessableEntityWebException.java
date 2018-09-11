package info.setmy.exceptions.web;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class UnprocessableEntityWebException extends WebException {

    @Override
    public int getStatusCode() {
        return 422;
    }

    public UnprocessableEntityWebException(final String string) {
        super(string);
    }
}
