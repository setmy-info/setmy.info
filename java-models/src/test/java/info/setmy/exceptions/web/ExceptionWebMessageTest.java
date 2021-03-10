package info.setmy.exceptions.web;

import info.setmy.models.web.WebError;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class ExceptionWebMessageTest {

    private final static String KEY = "error.message.key";

    @Test
    public void gettingWebErrorObjectFromBadRequestWebException() {
        final WebError webError = new BadRequestWebException(KEY).getWebError();
        assertThat(webError.getKey()).isEqualTo(KEY);
        assertThat(webError.getStatusCode()).isEqualTo(400);
    }

    @Test
    public void gettingWebErrorObjectFromUnauthorizedWebException() {
        final WebError webError = new UnauthorizedWebException(KEY).getWebError();
        assertThat(webError.getKey()).isEqualTo(KEY);
        assertThat(webError.getStatusCode()).isEqualTo(401);
    }

    @Test
    public void gettingWebErrorObjectFromForbiddenWebException() {
        final WebError webError = new ForbiddenWebException(KEY).getWebError();
        assertThat(webError.getKey()).isEqualTo(KEY);
        assertThat(webError.getStatusCode()).isEqualTo(403);
    }

    @Test
    public void gettingWebErrorObjectFromNotFoundWebException() {
        final WebError webError = new NotFoundWebException(KEY).getWebError();
        assertThat(webError.getKey()).isEqualTo(KEY);
        assertThat(webError.getStatusCode()).isEqualTo(404);
    }
}
