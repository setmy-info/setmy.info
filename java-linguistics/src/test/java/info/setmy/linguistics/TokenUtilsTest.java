package info.setmy.linguistics;

import info.setmy.linguistics.models.token.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static info.setmy.linguistics.TokenUtils.toToken;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TokenUtilsTest {

    Token token;

    @ParameterizedTest
    @ValueSource(chars = {'a', 'A', 'ü'})
    public void alphabet(final Character character) {
        token = toToken(character);
        assertThat(token).isInstanceOf(AlphabeticCharacterToken.class);
        assertTrue(token.isAlphabeticCharacterToken());
        assertTrue(token.isAlphaNumericCharacterToken());
        assertTrue(token.isNotWhiteCharToken());
    }

    @ParameterizedTest
    @ValueSource(chars = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'})
    public void numeric(final Character character) {
        token = toToken(character);
        assertThat(token).isInstanceOf(NumericCharacterToken.class);
        assertTrue(token.isNumericCharacterToken());
        assertTrue(token.isAlphaNumericCharacterToken());
        assertTrue(token.isNotWhiteCharToken());
    }

    @ParameterizedTest
    @ValueSource(chars = {'\n'})
    public void lineEnding(final Character character) {
        token = toToken(character);
        assertThat(token).isInstanceOf(LineEndingToken.class);
        assertTrue(token.isLineEndingToken());

    }

    @ParameterizedTest
    @ValueSource(chars = {' ', '\r', '\n', '\t'})
    public void whiteChars(final Character character) {
        token = toToken(character);
        assertThat(token).isInstanceOfAny(WhiteCharToken.class, LineEndingToken.class);
        assertTrue(token.isWhiteCharToken());
    }

    @ParameterizedTest
    @ValueSource(chars = {'.', '!', '?'})
    public void sentenceEndingChars(final Character character) {
        token = toToken(character);
        assertThat(token).isInstanceOf(SentenceEndingToken.class);
        assertTrue(token.isSentenceEndingToken());
        assertTrue(token.isNotWhiteCharToken());
    }

    @ParameterizedTest
    @ValueSource(chars = {',', '-', '~', ';', ':'})
    public void phraseSeparatorChars(final Character character) {
        token = toToken(character);
        assertThat(token).isInstanceOf(PhraseSeparatorToken.class);
        assertTrue(token.isPhraseSeparatorToken());
        assertTrue(token.isNotWhiteCharToken());
    }

    @ParameterizedTest
    @ValueSource(chars = {'\'', '\"', '`', '´'})
    public void soloQuoteSeparatorChars(final Character character) {
        token = toToken(character);
        assertThat(token).isInstanceOf(SoloQuotationToken.class);
    }

    @ParameterizedTest
    @ValueSource(chars = {'«', '‘', '“', '„'})
    public void pairedBeginChars(final Character character) {
        token = toToken(character);
        assertThat(token).isInstanceOf(BeginPairedQuotationToken.class);
    }

    @ParameterizedTest
    @ValueSource(chars = {'»', '’', '”'})
    public void pairedEndChars(final Character character) {
        token = toToken(character);
        assertThat(token).isInstanceOf(EndPairedQuotationToken.class);
    }

    @ParameterizedTest
    @ValueSource(chars = {'(', '{', '[', '<'})
    public void pairedBlockBeginChars(final Character character) {
        token = toToken(character);
        assertThat(token).isInstanceOf(BeginBlockToken.class);
    }

    @ParameterizedTest
    @ValueSource(chars = {')', '}', ']', '>'})
    public void pairedBlockEndChars(final Character character) {
        token = toToken(character);
        assertThat(token).isInstanceOf(EndBlockToken.class);
    }
}
