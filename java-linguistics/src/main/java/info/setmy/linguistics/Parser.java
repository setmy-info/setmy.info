package info.setmy.linguistics;

import info.setmy.linguistics.models.token.Token;

import java.util.List;

public class Parser {

    private final ParsingHandler parsingHandler = new ParsingHandler();

    public List<Token> parse(final String content) {
        final ParseTraversal traversal = new ParseTraversal(content.toCharArray());
        for (char character : traversal.getCharacters()) {
            parsingHandler.hande(traversal.setCurrentToken(character).setNextToken());
            traversal.incrementIndex();
        }
        traversal.getParsingData().addWordTokenAndMakeNewCollector();
        return traversal.getParsingData().getParsedTokens();
    }
}
