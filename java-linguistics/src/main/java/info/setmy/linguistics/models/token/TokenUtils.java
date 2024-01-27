package info.setmy.linguistics.models.token;

import static info.setmy.linguistics.models.token.TokenConstants.ALL_TOKENS;
import java.util.regex.Pattern;
import static java.util.regex.Pattern.matches;

public class TokenUtils {

    public static Token toToken(final char character) {
        return ALL_TOKENS.stream()
                .filter(token -> token.equals(character))
                .findFirst()
                .orElse(toTextualToken(character));
    }

    private static Token toTextualToken(final char character) {
        if (matches("[0-9]", "" + character)) {
            return null;
        } else {
            return null;
        }
    }

    /*
    public static boolean isLineEndingCharacter(final char character) {
        return have(character, LINE_ENDING_TOKENS);
    }

    public static boolean isSentenceEndingSeparator(final char character) {
        return have(character, SENTENCE_ENDING_TOKENS);
    }

    public static boolean isClauseSeparator(final char character) {
        return have(character, CLAUSE_SEPARATOR_TOKENS);
    }

    public static boolean isSingleQuoteSeparator(final char character) {
        return have(character, SOLO_QUOTE_SEPARATORS);
    }

    public static boolean isBlockSeparatorBeginning(final char character) {
        return have(character, PAIRED_BLOCK_SEPARATOR_BEGINNINGS);
    }

    public static boolean isBlockSeparatorEnd(final char character) {
        return have(character, PAIRED_BLOCK_SEPARATOR_ENDINGS);
    }

    public static boolean isPairedQuotesBeginning(final char character) {
        return have(character, PAIRED_QUOTE_SEPARATOR_BEGINNINGS);
    }

    public static boolean isPairedQuotesEnd(final char character) {
        return have(character, PAIRED_QUOTE_SEPARATOR_ENDINGS);
    }

    public static boolean isWhiteCharacter(final char character) {
        return have(character, WHITE_CHAR_TOKENS);
    }

    public static boolean have(final char character, final Token[] array) {
        for (var token : array) {
            if (token.is(character)) {
                return true;
            }
        }
        return false;
    }
     */
}
