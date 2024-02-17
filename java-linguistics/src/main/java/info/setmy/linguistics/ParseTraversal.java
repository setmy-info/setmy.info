package info.setmy.linguistics;

import info.setmy.linguistics.models.token.Token;
import info.setmy.linguistics.models.token.WordToken;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

import static info.setmy.linguistics.models.token.TokenUtils.toToken;

@Getter
@Setter
@Accessors(chain = true)
public class ParseTraversal {

    private Token previousToken;
    private Token token;

    private List<Token> parsedTokens = new ArrayList<>();
    private StringBuilder parsingToken = new StringBuilder();

    public ParseTraversal setToken(final char character) {
        setPreviousToken();
        return this.setToken(toToken(character));
    }

    public ParseTraversal setToken(final Token token) {
        this.token = token;
        return this;
    }

    public void finishParsingToken() {
        final Token textToken = new WordToken(parsingToken.toString());
        parsingToken = new StringBuilder();
        parsedTokens.add(textToken);
    }

    public ParseTraversal setPreviousToken() {
        return setPreviousToken(getToken());
    }

    public boolean previousTokenDoesntExists() {
        return !previousTokenExists();
    }

    public boolean previousTokenExists() {
        return previousToken != null;
    }
}
