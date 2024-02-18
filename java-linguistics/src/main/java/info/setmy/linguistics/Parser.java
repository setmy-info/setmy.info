package info.setmy.linguistics;

import info.setmy.linguistics.models.token.Token;

import java.util.List;

public class Parser {

    private final ParsingHandler parsingHandler = new ParsingHandler();

    public List<Token> parse(final String content) {
        final char[] chars = content.toCharArray();
        final ParseTraversal traversal = new ParseTraversal();
        parsingHandler.handleStartParsing(traversal);
        for (char character : chars) {
            traversal.setCurrentToken(character);
            detectTokenTypeChange(traversal);
            parsingHandler.handleToken(traversal);
        }
        parsingHandler.handleEndParsing(traversal);
        return traversal.getParsedTokens();
    }

    private void detectTokenTypeChange(final ParseTraversal traversal) {
        if (parsingHandler.isTypeChanged(traversal)) {
            parsingHandler.handleTypeChange(traversal);
        }
    }
}
