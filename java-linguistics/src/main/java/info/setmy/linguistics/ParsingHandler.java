package info.setmy.linguistics;

public class ParsingHandler {

    public void hande(final ParseTraversal traversal) {
        if (traversal.getCurrentToken().isAlphaNumericCharacterToken()) {
            appendCurrentToken(traversal);
        } else if (traversal.getCurrentToken().isWhiteCharToken()) {
            finishParsingWord(traversal);
        } else if (traversal.getCurrentToken().isPhraseSeparatorToken()) {
            if (traversal.getNextToken().isWhiteCharToken()) {
                finishParsingWord(traversal);
                traversal.getParsingData().add(traversal.getCurrentToken());
            } else {
                appendCurrentToken(traversal);
            }
        } else if (traversal.getCurrentToken().isSentenceEndingToken()) {
            if (traversal.getNextToken().isWhiteCharToken()) {
                finishParsingWord(traversal);
                traversal.getParsingData().add(traversal.getCurrentToken());
            } else {
                appendCurrentToken(traversal);

            }
        }
    }

    private void finishParsingWord(ParseTraversal traversal) {
        traversal.getParsingData().addWordTokenAndNewBuilder();
    }

    private void appendCurrentToken(final ParseTraversal traversal) {
        traversal.getParsingData().getWordTokenBuilder().append(traversal.getCurrentToken().getValue());
    }
}
