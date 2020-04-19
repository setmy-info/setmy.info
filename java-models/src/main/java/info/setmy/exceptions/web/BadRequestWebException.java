package info.setmy.exceptions.web;

import static info.setmy.models.ErrorCodes.BAD_REQUEST;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class BadRequestWebException extends WebException {

    @Override
    public int getStatusCode() {
        return BAD_REQUEST;
    }

    public BadRequestWebException(final String string) {
        super(string);
    }
}
