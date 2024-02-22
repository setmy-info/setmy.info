package info.setmy.linguistics;

import info.setmy.linguistics.models.token.Token;
import info.setmy.linguistics.models.token.WordToken;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

import static info.setmy.linguistics.models.token.TokenUtils.toToken;

@Getter
@Setter
@Accessors(chain = true)
@RequiredArgsConstructor
public class ParseTraversal {

    private final char[] characters;
    private Token previousToken;
    private Token currentToken;
    private int index;

    private List<Token> parsedTokens = new ArrayList<>();
    private StringBuilder textTokenBuilder = new StringBuilder();

    public ParseTraversal(final String string) {
        this(string.toCharArray());
    }

    public ParseTraversal setCurrentToken(final char character) {
        setPreviousToken();
        return setCurrentToken(toToken(character));
    }

    public ParseTraversal setCurrentToken(final Token token) {
        this.currentToken = token;
        return this;
    }

    public void finishParsingWord() {
        addNewWordToken();
        newStringBuilder();
    }

    public boolean addNewWordToken() {
        return parsedTokens.add(newWordToken());
    }

    public Token newWordToken() {
        return new WordToken(textTokenBuilder.toString());
    }

    public void newStringBuilder() {
        textTokenBuilder = new StringBuilder();
    }

    public ParseTraversal setPreviousToken() {
        return setPreviousToken(getCurrentToken());
    }

    public void incrementIndex() {
        index++;
    }

    boolean haveNoPreviousToken() {
        return !havePreviousToken();
    }

    boolean havePreviousToken() {
        return previousToken != null;
    }
}
