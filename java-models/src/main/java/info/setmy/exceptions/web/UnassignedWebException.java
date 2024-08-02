package info.setmy.exceptions.web;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public abstract class UnassignedWebException extends WebException {

    public UnassignedWebException(final String string) {
        super(string);
    }
}
