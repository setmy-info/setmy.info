package info.setmy.linguistics;

public class ParsingHandler {

    public void handleStartParsing(final ParseTraversal traversal) {
    }

    public void handleEndParsing(final ParseTraversal traversal) {
        traversal.finishParsingToken();
    }

    public boolean isTypeChanged(final ParseTraversal traversal) {
        if (traversal.isPreviousNullToAnyChange()) {
            return true;
        }
        if (traversal.isClassTypeChange()
            && traversal.isNonTextToTextChange()
            && traversal.isTextToNonTextChange()) {
            return true;
        }
        return false;
    }

    public void handleTypeChange(final ParseTraversal traversal) {
    }

    public void handleToken(final ParseTraversal traversal) {
        if (traversal.getCurrentToken().isAlphabeticCharacterToken()) {
            traversal.getTextTokenBuilder().append(traversal.getCurrentToken().getValue());
        }
    }
}
