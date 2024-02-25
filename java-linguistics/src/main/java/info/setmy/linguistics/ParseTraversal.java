package info.setmy.linguistics;

import info.setmy.linguistics.models.token.Token;
import info.setmy.linguistics.models.token.WhiteCharToken;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import static info.setmy.linguistics.TokenUtils.toToken;

@Getter
@Setter
@Accessors(chain = true)
@RequiredArgsConstructor
public class ParseTraversal {

    private final char[] characters;
    private final ParsingData parsingData = new ParsingData();
    private Token previousToken;
    private Token currentToken;
    private Token nextToken;
    private int index;

    public ParseTraversal(final String string) {
        this(string.toCharArray());
    }

    public ParseTraversal setCurrentToken(final char character) {
        previousToken = currentToken;
        return setCurrentToken(toToken(character));
    }

    public ParseTraversal setCurrentToken(final Token token) {
        this.currentToken = token;
        return this;
    }

    public ParseTraversal setNextToken() {
        final int nextIndex = index + 1;
        if (nextIndex < characters.length) {
            return setNextToken(characters[nextIndex]);
        }
        return setNextToken(new WhiteCharToken(" "));
    }

    public ParseTraversal setNextToken(final char character) {
        return setNextToken(toToken(character));
    }

    public ParseTraversal setNextToken(final Token token) {
        this.nextToken = token;
        return this;
    }

    public void incrementIndex() {
        index++;
    }
}
