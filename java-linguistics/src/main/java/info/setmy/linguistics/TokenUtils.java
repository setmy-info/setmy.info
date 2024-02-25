package info.setmy.linguistics;

import info.setmy.linguistics.models.token.AlphabeticCharacterToken;
import info.setmy.linguistics.models.token.NumericCharacterToken;
import info.setmy.linguistics.models.token.Token;

import java.util.regex.Pattern;

import static info.setmy.linguistics.models.token.TokenConstants.ALL_TOKENS;

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
