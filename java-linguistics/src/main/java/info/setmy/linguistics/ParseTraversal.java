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
    private Token currentToken;

    private List<Token> parsedTokens = new ArrayList<>();
    private StringBuilder textTokenBuilder = new StringBuilder();

    public ParseTraversal setCurrentToken(final char character) {
        setPreviousToken();
        return setCurrentToken(toToken(character));
    }

    public ParseTraversal setCurrentToken(final Token token) {
        this.currentToken = token;
        return this;
    }

    public void finishParsingToken() {
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

    public boolean isClassTypeChange() {
        return getPreviousToken().getClass() != getCurrentToken().getClass();
    }

    public boolean isTextToNonTextChange() {
        return getPreviousToken().isTextCharacterToken() || !getCurrentToken().isTextCharacterToken();
    }

    public boolean isNonTextToTextChange() {
        return !getPreviousToken().isTextCharacterToken() || getCurrentToken().isTextCharacterToken();
    }

    public boolean isPreviousNullToAnyChange() {
        return getPreviousToken() == null && getCurrentToken() != null;
    }
}
