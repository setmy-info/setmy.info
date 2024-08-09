package info.setmy.jwt.models;

import static info.setmy.jwt.models.Data.SERVICE_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 *
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class ExtendedTokenTest extends Data {

    @BeforeEach
    public void beforeEach() {
        token = new ExtendedJWTToken(SERVICE_NAME);
    }

    @Test
    @DisplayName("Given token should have key from service")
    public void keyFromService() {
        assertThat(token.getKey()).isEqualTo(EXAMPLE_SECRET_KEY);
    }

    @Test
    @DisplayName("Given token should have issuer from service")
    public void issuerFromService() {
        assertThat(token.getIssuer()).isEqualTo(ISSUER);
    }

    @Test
    public void sessionMinutesFromService() {
        assertThat(token.getExpirationMinutes()).isEqualTo(EXAMPLE_SESSION_MINUTES);
    }
}
