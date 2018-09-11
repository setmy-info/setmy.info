package info.setmy.exceptions.reports;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class CompilationException extends ReportException {

    public CompilationException() {
        super();
    }

    public CompilationException(final String string) {
        super(string);
    }

    public CompilationException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public CompilationException(Throwable throwable) {
        super(throwable);
    }

    protected CompilationException(String string, Throwable throwable, boolean bln, boolean bln1) {
        super(string, throwable, bln, bln1);
    }
}
