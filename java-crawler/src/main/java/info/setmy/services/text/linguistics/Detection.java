package info.setmy.services.text.linguistics;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class Detection {

    /*
    https://en.wikipedia.org/wiki/Quotation_mark

    quotation marks   ‘ ’  “ ”  ' '  " "
    angular quotation marks «»
    apostrophe   ’ ' „ “
    curved quotation marks “”
     */
    private static final char[] LINE_ENDING = {'\n'};
    private static final char[] WHITE_CHAR = {' ', '\r', '\t'};
    private static final char[] SENTENCE_ENDINGS = {'.', '!', '?'};
    private static final char[] CLAUSE_SEPARATORS = {',', '-', ';', ':'};
    // APOSTROPHE, QUOTATION, GRAVE_ACCENT, ACUTE_ACCENT
    private static final char[] SOLO_QUOTE_SEPARATORS = {'\'', '\"', '`', '´'};
    // angular quotation marks, curved apostrophe, Double curved apostrophe, diagonal curved quotation marks
    private static final char[] PAIRED_QUOTE_SEPARATOR_BEGINNINGS = {'«', '‘', '“', '„'};
    private static final char[] PAIRED_QUOTE_SEPARATOR_ENDINGS = {'»', '’', '”'};
    private static final int[][] QUOTE_PAIRS = new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 2}};
    //ROUND_BRACKETS_BLOCK, CURLY_BRACKETS_BLOCK, SQUARE_BRACKETS_BLOCK, ANGLE_BRACKETS_BLOCK
    private static final char[] PAIRED_BLOCK_SEPARATOR_BEGINNINGS = {'(', '{', '[', '<'};
    private static final char[] PAIRED_BLOCK_SEPARATOR_ENDINGS = {')', '}', ']', '>'};

    boolean isLineSeparatorCharacter(final char character) {
        return have(character, LINE_ENDING);
    }

    boolean isSentenceSeparator(final char character) {
        return have(character, SENTENCE_ENDINGS);
    }

    boolean isClauseSeparator(final char character) {
        return have(character, CLAUSE_SEPARATORS);
    }

    boolean isSingleQuoteSeparator(final char character) {
        return have(character, SOLO_QUOTE_SEPARATORS);
    }

    boolean isBlockSeparatorBeginning(final char character) {
        return have(character, PAIRED_BLOCK_SEPARATOR_BEGINNINGS);
    }

    boolean isBlockSeparatorEnd(final char character) {
        return have(character, PAIRED_BLOCK_SEPARATOR_ENDINGS);
    }

    boolean isPairedQuotesBeginning(final char character) {
        return have(character, PAIRED_QUOTE_SEPARATOR_BEGINNINGS);
    }

    boolean isPairedQuotesEnd(final char character) {
        return have(character, PAIRED_QUOTE_SEPARATOR_ENDINGS);
    }

    boolean arePairedQuotes(final char a, final char b) {
        return arePaired(a, b, QUOTE_PAIRS, PAIRED_QUOTE_SEPARATOR_BEGINNINGS, PAIRED_QUOTE_SEPARATOR_ENDINGS);
    }

    boolean arePaired(final char begin, final char end, final int[][] table, final char[] beginnings, final char[] ends) {
        for (int[] pair : table) {
            char BEGIN = beginnings[pair[0]];
            char END = ends[pair[1]];
            if (begin == BEGIN && end == END) {
                return true;
            }
        }
        return false;
    }

    boolean isWhiteCharacter(final char character) {
        return have(character, WHITE_CHAR);
    }

    boolean have(final char character, final char[] array) {
        for (char chr : array) {
            if (chr == character) {
                return true;
            }
        }
        return false;
    }
}
