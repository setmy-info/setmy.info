package info.setmy.models.textfunctions.tokens;

public class TokenBuilder {

    private final StringBuilder stringBuilder = new StringBuilder();

    private final TokenType tokenType;

    public TokenBuilder(final TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public TokenBuilder add(final char character) {
        stringBuilder.append(character);
        return this;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public String toString() {
        return stringBuilder.toString();
    }
}
