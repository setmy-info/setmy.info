package info.setmy.linguistics;

import info.setmy.linguistics.models.token.ParsingEventType;
import info.setmy.linguistics.models.token.Token;

import static info.setmy.linguistics.models.token.TokenUtils.toToken;

public class ParseTraversal {

    private ParsingEventType parsingEventType;

    private Token token;

    public ParsingEventType getParsingEventType() {
        return parsingEventType;
    }

    public ParseTraversal setParsingEventType(final ParsingEventType parsingEventType) {
        this.parsingEventType = parsingEventType;
        return this;
    }

    public Token getToken() {
        return token;
    }

    public ParseTraversal setToken(final char character) {
        return setToken(toToken(character));
    }

    public ParseTraversal setToken(final Token token) {
        this.token = token;
        return this;
    }
}
