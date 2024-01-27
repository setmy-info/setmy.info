package info.setmy.linguistics.models.token;

import static info.setmy.linguistics.models.token.TokenUtils.toToken;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class TokenUtilsTest {

    @Test
    public void tokens() {
        var token = toToken('a');
        assertThat(token).isInstanceOf(TextCharacterToken.class);
    }
}
