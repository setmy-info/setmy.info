package info.setmy.linguistics.models.token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class TokenConstants {

    /*
    https://en.wikipedia.org/wiki/Quotation_mark

    quotation marks   ‘ ’  “ ”  ' '  " "
    angular quotation marks «»
    apostrophe   ’ ' „ “
    curved quotation marks “”
     */
    public static final Token[] LINE_ENDING_TOKENS = {new LineEndingToken('\n')};
    public static final Token[] WHITE_CHAR_TOKENS = {new WhiteCharToken(' '), new WhiteCharToken('\r'), new WhiteCharToken('\t')};
    public static final Token[] SENTENCE_ENDING_TOKENS = {new SentenceEndingToken('.'), new SentenceEndingToken('!'), new SentenceEndingToken('?')};
    public static final Token[] CLAUSE_SEPARATOR_TOKENS = {new PhraseSeparatorToken(','), new PhraseSeparatorToken('-'), new PhraseSeparatorToken(';'), new PhraseSeparatorToken(':')};

    // APOSTROPHE, QUOTATION, GRAVE_ACCENT, ACUTE_ACCENT
    public static final Token SOLO_QUOTE_SEPARATORS[] = {new SoloQuotationToken('\''), new SoloQuotationToken('\"'), new SoloQuotationToken('`'), new SoloQuotationToken('´')};

    // angular quotation marks, curved apostrophe, Double curved apostrophe, diagonal curved quotation marks
    public static final Token[] PAIRED_QUOTE_SEPARATOR_BEGINNINGS = {new BeginPairedQuotationToken('«'), new BeginPairedQuotationToken('‘'), new BeginPairedQuotationToken('“'), new BeginPairedQuotationToken('„')};
    public static final Token[] PAIRED_QUOTE_SEPARATOR_ENDINGS = {new EndPairedQuotationToken('»'), new EndPairedQuotationToken('’'), new EndPairedQuotationToken('”')};
    public static final int[][] QUOTE_PAIRS = new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 2}};

    //ROUND_BRACKETS_BLOCK, CURLY_BRACKETS_BLOCK, SQUARE_BRACKETS_BLOCK, ANGLE_BRACKETS_BLOCK
    public static final Token[] PAIRED_BLOCK_SEPARATOR_BEGINNINGS = {new BeginBlockToken('('), new BeginBlockToken('{'), new BeginBlockToken('['), new BeginBlockToken('<')};
    public static final Token[] PAIRED_BLOCK_SEPARATOR_ENDINGS = {new EndBlockToken(')'), new EndBlockToken('}'), new EndBlockToken(']'), new EndBlockToken('>')};

    public static final List<Token> ALL_TOKENS;

    static {
        final List<Token> all = new ArrayList<>();
        all.addAll(Arrays.asList(LINE_ENDING_TOKENS));
        all.addAll(Arrays.asList(WHITE_CHAR_TOKENS));
        all.addAll(Arrays.asList(SENTENCE_ENDING_TOKENS));
        all.addAll(Arrays.asList(CLAUSE_SEPARATOR_TOKENS));
        all.addAll(Arrays.asList(SOLO_QUOTE_SEPARATORS));
        all.addAll(Arrays.asList(PAIRED_QUOTE_SEPARATOR_BEGINNINGS));
        all.addAll(Arrays.asList(PAIRED_QUOTE_SEPARATOR_ENDINGS));
        all.addAll(Arrays.asList(PAIRED_BLOCK_SEPARATOR_BEGINNINGS));
        all.addAll(Arrays.asList(PAIRED_BLOCK_SEPARATOR_ENDINGS));
        ALL_TOKENS = all;
    }

    private TokenConstants() {
    }
}
