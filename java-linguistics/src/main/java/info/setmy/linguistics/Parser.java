package info.setmy.linguistics;

import info.setmy.linguistics.models.token.ParsingEventType;

import java.util.ArrayList;
import java.util.List;

import static info.setmy.linguistics.models.token.ParsingEventType.CHARACTER_HANDLING;
import static info.setmy.linguistics.models.token.ParsingEventType.PARSING_END;
import static info.setmy.linguistics.models.token.ParsingEventType.PARSING_START;

public class Parser {

    private final List<ParsingHandler> handlers = new ArrayList<>();

    public void parse(final String content) {
        final char[] chars = content.toCharArray();
        final ParseTraversal traversal = new ParseTraversal();
        emitEvent(traversal.setParsingEventType(PARSING_START));
        for (char character : chars) {
            detectEvents(traversal.setToken(character))
                .forEach(parsingEventType -> emitEvent(traversal.setParsingEventType(parsingEventType)));
            emitEvent(traversal.setParsingEventType(CHARACTER_HANDLING));
        }
        emitEvent(traversal.setParsingEventType(PARSING_END));
    }

    private void emitEvent(final ParseTraversal traversal) {
        handlers.forEach(parsingHandler -> parsingHandler.handle(traversal));
    }

    private List<ParsingEventType> detectEvents(final ParseTraversal parseTraversal) {
        final List<ParsingEventType> result = new ArrayList<>();

        return result;
    }
}
