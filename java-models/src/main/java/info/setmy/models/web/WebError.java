package info.setmy.models.web;

import java.util.List;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class WebError {

    private final String key;

    private final int statusCode;

    private final List<String> parameters;

    public WebError(final String key, final int statusCode, final List<String> parameters) {
        this.key = key;
        this.statusCode = statusCode;
        this.parameters = parameters;
    }

    public String getKey() {
        return key;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public List<String> getParameters() {
        return parameters;
    }
}
