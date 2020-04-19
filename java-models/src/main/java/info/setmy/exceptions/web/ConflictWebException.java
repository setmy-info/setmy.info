package info.setmy.exceptions.web;

import static info.setmy.models.ErrorCodes.CONFLICT;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class ConflictWebException extends WebException {

    @Override
    public int getStatusCode() {
        return CONFLICT;
    }

    public ConflictWebException(final String string) {
        super(string);
    }
}
