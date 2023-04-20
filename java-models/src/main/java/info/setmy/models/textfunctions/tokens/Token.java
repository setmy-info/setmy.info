package info.setmy.models.textfunctions.tokens;

import java.util.Objects;

public class Token {

    private final TokenType tokenType;

    private final String value;

    public Token(final TokenType tokenType, final String value) {
        this.tokenType = tokenType;
        this.value = value;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return tokenType == token.tokenType && Objects.equals(value, token.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokenType, value);
    }
}
