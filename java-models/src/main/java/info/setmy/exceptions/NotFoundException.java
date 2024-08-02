package info.setmy.exceptions;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class NotFoundException extends DoesNotExistException {

    public NotFoundException() {
        super();
    }

    public NotFoundException(final String string) {
        super(string);
    }

    public NotFoundException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public NotFoundException(Throwable throwable) {
        super(throwable);
    }

    protected NotFoundException(String string, Throwable throwable, boolean bln, boolean bln1) {
        super(string, throwable, bln, bln1);
    }
}
