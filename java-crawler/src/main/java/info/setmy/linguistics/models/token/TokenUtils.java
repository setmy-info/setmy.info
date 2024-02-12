package info.setmy.linguistics.models.token;

import static info.setmy.linguistics.models.token.TokenConstants.ALL_TOKENS;
import java.util.regex.Pattern;

public class TokenUtils {

    private final static Pattern NUMBERS_PATTERN = Pattern.compile("[0-9]");

    public static Token toToken(final char character) {
        return ALL_TOKENS.stream()
                .filter(token -> token.equals(character))
                .findFirst()
                .orElse(toTextualToken(character));
    }

    private static Token toTextualToken(final char character) {
        var matcher = NUMBERS_PATTERN.matcher("" + character);
        if (matcher.matches()) {
            return new NumericCharacterToken(character);
        } else {
            return new AlphabeticCharacterToken(character);
        }
    }
}
