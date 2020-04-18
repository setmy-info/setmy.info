package info.setmy.models.web;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class WebErrorTest {

    WebError webError;

    @Test
    public void gettersAfterConstruction() {
        final String key = "super.fancy.key.1234567890";
        final int statusCode = 1234;
        final List<String> parameters = new ArrayList<>();
        webError = new WebError(key, statusCode, parameters);
        assertThat(webError.getKey(), is(equalTo(key)));
        assertThat(webError.getStatusCode(), is(equalTo(statusCode)));
        assertSame(parameters, webError.getParameters());
    }
}
