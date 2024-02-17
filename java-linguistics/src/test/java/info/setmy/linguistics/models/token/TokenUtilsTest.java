package info.setmy.linguistics.models.token;

import org.junit.jupiter.api.Test;

import static info.setmy.linguistics.models.token.TokenUtils.toToken;
import static org.assertj.core.api.Assertions.assertThat;

public class TokenUtilsTest {

    @Test
    public void alphabet() {
        assertThat(toToken('a')).isInstanceOf(AlphabeticCharacterToken.class);
    }

    @Test
    public void numeric() {
        assertThat(toToken('1')).isInstanceOf(NumericCharacterToken.class);
        assertThat(toToken('2')).isInstanceOf(NumericCharacterToken.class);
        assertThat(toToken('3')).isInstanceOf(NumericCharacterToken.class);
        assertThat(toToken('4')).isInstanceOf(NumericCharacterToken.class);
        assertThat(toToken('5')).isInstanceOf(NumericCharacterToken.class);
        assertThat(toToken('6')).isInstanceOf(NumericCharacterToken.class);
        assertThat(toToken('7')).isInstanceOf(NumericCharacterToken.class);
        assertThat(toToken('8')).isInstanceOf(NumericCharacterToken.class);
        assertThat(toToken('9')).isInstanceOf(NumericCharacterToken.class);
        assertThat(toToken('0')).isInstanceOf(NumericCharacterToken.class);
    }

    @Test
    public void lineEnding() {
        assertThat(toToken('\n')).isInstanceOf(LineEndingSingleToken.class);
    }

    @Test
    public void whiteChars() {
        assertThat(toToken(' ')).isInstanceOf(WhiteCharSingleToken.class);
        assertThat(toToken('\r')).isInstanceOf(WhiteCharSingleToken.class);
        assertThat(toToken('\t')).isInstanceOf(WhiteCharSingleToken.class);
    }

    @Test
    public void sentenceEndingChars() {
        assertThat(toToken('.')).isInstanceOf(SentenceEndingSingleToken.class);
        assertThat(toToken('!')).isInstanceOf(SentenceEndingSingleToken.class);
        assertThat(toToken('?')).isInstanceOf(SentenceEndingSingleToken.class);
    }

    @Test
    public void clauseSeparatorChars() {
        assertThat(toToken(',')).isInstanceOf(ClauseSeparatorSingleToken.class);
        assertThat(toToken('-')).isInstanceOf(ClauseSeparatorSingleToken.class);
        assertThat(toToken(';')).isInstanceOf(ClauseSeparatorSingleToken.class);
        assertThat(toToken(':')).isInstanceOf(ClauseSeparatorSingleToken.class);
    }

    @Test
    public void soloQuoteSeparatorChars() {
        assertThat(toToken('\'')).isInstanceOf(SoloCharacterPairedToken.class);
        assertThat(toToken('\"')).isInstanceOf(SoloCharacterPairedToken.class);
        assertThat(toToken('`')).isInstanceOf(SoloCharacterPairedToken.class);
        assertThat(toToken('´')).isInstanceOf(SoloCharacterPairedToken.class);
    }

    @Test
    public void pairedBeginChars() {
        assertThat(toToken('«')).isInstanceOf(BeginDoubleCharacterPairedToken.class);
        assertThat(toToken('‘')).isInstanceOf(BeginDoubleCharacterPairedToken.class);
        assertThat(toToken('“')).isInstanceOf(BeginDoubleCharacterPairedToken.class);
        assertThat(toToken('„')).isInstanceOf(BeginDoubleCharacterPairedToken.class);
    }

    @Test
    public void pairedEndChars() {
        assertThat(toToken('»')).isInstanceOf(EndDoubleCharacterPairedToken.class);
        assertThat(toToken('’')).isInstanceOf(EndDoubleCharacterPairedToken.class);
        assertThat(toToken('”')).isInstanceOf(EndDoubleCharacterPairedToken.class);
    }

    @Test
    public void pairedBlockBeginChars() {
        assertThat(toToken('(')).isInstanceOf(BeginPairedBlockToken.class);
        assertThat(toToken('{')).isInstanceOf(BeginPairedBlockToken.class);
        assertThat(toToken('[')).isInstanceOf(BeginPairedBlockToken.class);
        assertThat(toToken('<')).isInstanceOf(BeginPairedBlockToken.class);
    }

    @Test
    public void pairedBlockEndChars() {
        assertThat(toToken(')')).isInstanceOf(EndPairedBlockToken.class);
        assertThat(toToken('}')).isInstanceOf(EndPairedBlockToken.class);
        assertThat(toToken(']')).isInstanceOf(EndPairedBlockToken.class);
        assertThat(toToken('>')).isInstanceOf(EndPairedBlockToken.class);
    }
}
