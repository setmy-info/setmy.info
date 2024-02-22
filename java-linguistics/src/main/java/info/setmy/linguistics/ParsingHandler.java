package info.setmy.linguistics;

import info.setmy.linguistics.models.token.EventType;

public class ParsingHandler implements EventHandler {

    @Override
    public void hande(final EventType eventType, final ParseTraversal traversal) {
        switch (eventType) {
            case PARSING_BEGIN:
                doHandleStartParsing(traversal);
                break;
            case PARSING_END:
                doHandleEndParsing(traversal);
                break;
            case NULL_TO_ANY:
                doNullToAny(traversal);
                break;
            case ALPHABETIC_TO_WHITE:
                doAlphabeticToWhite(traversal);
                break;
            case ALPHA_NUMERIC_TO_ANY:
                doAlphaNumericToWhite(traversal);
                break;
            case WHITE_TO_ALPHABETIC:
                doWhiteToAlphabetic(traversal);
                break;
            case ANY_TO_ALPHA_NUMERIC:
                doWhiteToAlphaNumeric(traversal);
            default:
        }
    }

    private void doHandleStartParsing(final ParseTraversal traversal) {
    }

    private void doNullToAny(final ParseTraversal traversal) {
    }

    private void doAlphabeticToWhite(final ParseTraversal traversal) {
    }

    private void doAlphaNumericToWhite(final ParseTraversal traversal) {
    }

    private void doWhiteToAlphabetic(final ParseTraversal traversal) {
    }

    private void doWhiteToAlphaNumeric(final ParseTraversal traversal) {
    }

    private void doHandleEndParsing(final ParseTraversal traversal) {
        traversal.finishParsingToken();
    }
}
