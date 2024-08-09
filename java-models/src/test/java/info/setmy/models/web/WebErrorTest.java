package info.setmy.models.web;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class WebErrorTest {

    WebError webError;

    @Test
    public void gettersAfterConstruction() {
        final String key = "super.fancy.key.1234567890";
        final int statusCode = 1234;
        final List<String> parameters = new ArrayList<>();
        webError = new WebError(key, statusCode, parameters);
        assertThat(webError.getKey()).isEqualTo(key);
        assertThat(webError.getStatusCode()).isEqualTo(statusCode);
        assertSame(parameters, webError.getParameters());
    }
}
