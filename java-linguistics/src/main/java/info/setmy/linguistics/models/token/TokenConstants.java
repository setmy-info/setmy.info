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
    public static final Token[] LINE_ENDING_TOKENS = {new LineEndingSingleToken('\n')};
    public static final Token[] WHITE_CHAR_TOKENS = {new WhiteCharSingleToken(' '), new WhiteCharSingleToken('\r'), new WhiteCharSingleToken('\t')};
    public static final Token[] SENTENCE_ENDING_TOKENS = {new SentenceEndingSingleToken('.'), new SentenceEndingSingleToken('!'), new SentenceEndingSingleToken('?')};
    public static final Token[] CLAUSE_SEPARATOR_TOKENS = {new ClauseSeparatorSingleToken(','), new ClauseSeparatorSingleToken('-'), new ClauseSeparatorSingleToken(';'), new ClauseSeparatorSingleToken(':')};

    // APOSTROPHE, QUOTATION, GRAVE_ACCENT, ACUTE_ACCENT
    public static final Token SOLO_QUOTE_SEPARATORS[] = {new SoloCharacterPairedToken('\''), new SoloCharacterPairedToken('\"'), new SoloCharacterPairedToken('`'), new SoloCharacterPairedToken('´')};

    // angular quotation marks, curved apostrophe, Double curved apostrophe, diagonal curved quotation marks
    public static final Token[] PAIRED_QUOTE_SEPARATOR_BEGINNINGS = {new BeginDoubleCharacterPairedToken('«'), new BeginDoubleCharacterPairedToken('‘'), new BeginDoubleCharacterPairedToken('“'), new BeginDoubleCharacterPairedToken('„')};
    public static final Token[] PAIRED_QUOTE_SEPARATOR_ENDINGS = {new EndDoubleCharacterPairedToken('»'), new EndDoubleCharacterPairedToken('’'), new EndDoubleCharacterPairedToken('”')};
    public static final int[][] QUOTE_PAIRS = new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 2}};

    //ROUND_BRACKETS_BLOCK, CURLY_BRACKETS_BLOCK, SQUARE_BRACKETS_BLOCK, ANGLE_BRACKETS_BLOCK
    public static final Token[] PAIRED_BLOCK_SEPARATOR_BEGINNINGS = {new BeginPairedBlockToken('('), new BeginPairedBlockToken('{'), new BeginPairedBlockToken('['), new BeginPairedBlockToken('<')};
    public static final Token[] PAIRED_BLOCK_SEPARATOR_ENDINGS = {new EndPairedBlockToken(')'), new EndPairedBlockToken('}'), new EndPairedBlockToken(']'), new EndPairedBlockToken('>')};

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
