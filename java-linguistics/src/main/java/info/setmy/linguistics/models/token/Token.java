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
        return this instanceof AlphabeticCharacterToken;
    }

    public boolean isNumericCharacterToken() {
        return this instanceof NumericCharacterToken;
    }

    public boolean isTextCharacterToken() {
        return this instanceof TextCharacterToken;
    }
}
