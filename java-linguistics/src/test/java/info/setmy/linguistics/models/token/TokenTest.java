package info.setmy.linguistics.models.token;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TokenTest {

    Token token;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void isAlphabeticCharacterToken() {
        token = new AlphabeticCharacterToken('a');
        assertTrue(token.isAlphabeticCharacterToken());
        assertTrue(token.isAlphaNumericCharacterToken());
        assertFalse(token.isWhiteCharSingleToken());
    }

    @Test
    public void isNumericCharacterToken() {
        token = new NumericCharacterToken('1');
        assertTrue(token.isNumericCharacterToken());
        assertTrue(token.isAlphaNumericCharacterToken());
        assertFalse(token.isWhiteCharSingleToken());
    }

    @Test
    public void isWhiteCharSingleToken() {
        assertTrue(new WhiteCharSingleToken(' ').isWhiteCharSingleToken());
    }
}
