package info.setmy.exceptions.reports;

import info.setmy.exceptions.*;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class ReportException extends UncheckedException {

    public ReportException() {
        super();
    }

    public ReportException(final String string) {
        super(string);
    }

    public ReportException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public ReportException(Throwable throwable) {
        super(throwable);
    }

    protected ReportException(String string, Throwable throwable, boolean bln, boolean bln1) {
        super(string, throwable, bln, bln1);
    }
}
