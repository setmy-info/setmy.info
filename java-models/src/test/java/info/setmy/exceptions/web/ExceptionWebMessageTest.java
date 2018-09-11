package info.setmy.exceptions.web;

import info.setmy.models.web.WebError;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class ExceptionWebMessageTest {

    private final static String KEY = "error.message.key";

    @Test
    public void gettingWebErrorObjectFromBadRequestWebException() {
        final WebError webError = new BadRequestWebException(KEY).getWebError();
        assertThat(webError.getKey(), is(equalTo(KEY)));
        assertThat(webError.getStatusCode(), is(equalTo(400)));
    }

    @Test
    public void gettingWebErrorObjectFromUnauthorizedWebException() {
        final WebError webError = new UnauthorizedWebException(KEY).getWebError();
        assertThat(webError.getKey(), is(equalTo(KEY)));
        assertThat(webError.getStatusCode(), is(equalTo(401)));
    }

    @Test
    public void gettingWebErrorObjectFromForbiddenWebException() {
        final WebError webError = new ForbiddenWebException(KEY).getWebError();
        assertThat(webError.getKey(), is(equalTo(KEY)));
        assertThat(webError.getStatusCode(), is(equalTo(403)));
    }

    @Test
    public void gettingWebErrorObjectFromNotFoundWebException() {
        final WebError webError = new NotFoundWebException(KEY).getWebError();
        assertThat(webError.getKey(), is(equalTo(KEY)));
        assertThat(webError.getStatusCode(), is(equalTo(404)));
    }
}
