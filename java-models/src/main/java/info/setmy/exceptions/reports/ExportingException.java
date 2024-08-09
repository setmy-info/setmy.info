package info.setmy.exceptions.reports;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class ExportingException extends ReportException {

    public ExportingException() {
        super();
    }

    public ExportingException(final String string) {
        super(string);
    }

    public ExportingException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public ExportingException(Throwable throwable) {
        super(throwable);
    }

    protected ExportingException(String string, Throwable throwable, boolean bln, boolean bln1) {
        super(string, throwable, bln, bln1);
    }
}
