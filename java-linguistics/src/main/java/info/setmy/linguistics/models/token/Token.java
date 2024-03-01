package info.setmy.linguistics.models.token;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

import static java.util.Objects.hash;

@Getter
@RequiredArgsConstructor
public abstract class Token {

    private final String value;

    public Token(final char value) {
        this.value = "" + value;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof Token token) {
            return Objects.equals(value, token.value);
        }
        if (obj.getClass() == String.class) {
            final String str = (String) obj;
            return Objects.equals(value, str);
        }
        if (obj.getClass() == Character.class) {
            final String str = "" + (Character) obj;
            return Objects.equals(value, str);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return hash(value);
    }

    @Override
    public String toString() {
        return value == null ? "" : value;
    }

    public boolean isAlphabeticCharacterToken() {
        return isInstanceOf(AlphabeticCharacterToken.class);
    }

    public boolean isNumericCharacterToken() {
        return isInstanceOf(NumericCharacterToken.class);
    }

    public boolean isAlphaNumericCharacterToken() {
        return isInstanceOf(AlphaNumericCharacterToken.class);
    }

    public boolean isOtherTextualCharacterToken() {
        return isInstanceOf(OtherTextualCharacterToken.class);
    }

    public boolean isLineEndingToken() {
        return isInstanceOf(LineEndingToken.class);
    }

    public boolean isNotWhiteCharToken() {
        return !isWhiteCharToken();
    }

    public boolean isWhiteCharToken() {
        return isInstanceOf(WhiteCharToken.class) || isInstanceOf(LineEndingToken.class);
    }

    public boolean isPhraseSeparatorToken() {
        return isInstanceOf(PhraseSeparatorToken.class);
    }

    public boolean isSentenceEndingToken() {
        return isInstanceOf(SentenceEndingToken.class);
    }

    public boolean isSoloQuotationToken() {
        return isInstanceOf(SoloQuotationToken.class);
    }

    public boolean isPairedQuotationToken() {
        return isBeginPairedQuotationToken() || isEndPairedQuotationToken();
    }

    public boolean isBeginPairedQuotationToken() {
        return isInstanceOf(BeginPairedQuotationToken.class);
    }

    public boolean isEndPairedQuotationToken() {
        return isInstanceOf(EndPairedQuotationToken.class);
    }

    public boolean isBlockToken() {
        return isBeginBlockToken() || isEndBlockToken();
    }

    public boolean isBeginBlockToken() {
        return isInstanceOf(BeginBlockToken.class);
    }

    public boolean isEndBlockToken() {
        return isInstanceOf(EndBlockToken.class);
    }

    public boolean isInstanceOf(final Class<? extends Token> clazz) {
        return clazz.isInstance(this);
    }
}
