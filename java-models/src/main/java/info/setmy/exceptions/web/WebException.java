package info.setmy.exceptions.web;

import info.setmy.exceptions.UncheckedException;
import info.setmy.models.web.WebError;
import java.util.ArrayList;
import java.util.List;

/**
 * Web layer exceptions
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public abstract class WebException extends UncheckedException {

    public abstract int getStatusCode();

    private final static Throwable CUT_EXCEPTION = new Throwable("Cut of cause");

    private final List<String> parameters;

    public WebException(final String string) {
        super(string);
        parameters = new ArrayList<>();
    }

    public WebException(final String string, final List<String> parameters) {
        super(string);
        this.parameters = parameters;
    }

    public WebError getWebError() {
        return new WebError(getMessage(), getStatusCode(), getParameters());
    }

    @Override
    public synchronized Throwable getCause() {
        return CUT_EXCEPTION;
    }

    public List<String> getParameters() {
        return parameters;
    }
}
