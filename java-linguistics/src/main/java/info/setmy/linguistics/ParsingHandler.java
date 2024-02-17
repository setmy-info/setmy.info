package info.setmy.linguistics;

public class ParsingHandler {

    public void handleStartParsing(final ParseTraversal traversal) {
    }

    public void handleEndParsing(final ParseTraversal traversal) {
        traversal.finishParsingToken();
    }

    public boolean isChanged(final ParseTraversal traversal) {
        if (isPreviousNull(traversal)) {
            return true;
        }
        if (isClassTypeChange(traversal)
            && isNonTextToTextChange(traversal)
            && isTextToNonTextchange(traversal)) {
            return true;
        }
        return false;
    }

    public void handleTypeChange(final ParseTraversal traversal) {
        if (true) {

        }
    }

    public void handleToken(final ParseTraversal traversal) {
        if (traversal.getToken().isAlphabeticCharacterToken()) {
            traversal.getParsingToken().append(traversal.getToken().getValue());
        }
    }

    private static boolean isPreviousNull(final ParseTraversal traversal) {
        return traversal.previousTokenDoesntExists();
    }

    private static boolean isClassTypeChange(final ParseTraversal traversal) {
        return traversal.getPreviousToken().getClass() != traversal.getToken().getClass();
    }

    private static boolean isTextToNonTextchange(final ParseTraversal traversal) {
        return traversal.getPreviousToken().isTextCharacterToken() || !traversal.getToken().isTextCharacterToken();
    }

    private static boolean isNonTextToTextChange(final ParseTraversal traversal) {
        return !traversal.getPreviousToken().isTextCharacterToken() || traversal.getToken().isTextCharacterToken();
    }
}
