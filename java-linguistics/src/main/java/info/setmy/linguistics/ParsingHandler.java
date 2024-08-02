package info.setmy.linguistics;

import info.setmy.linguistics.models.token.Token;

public class ParsingHandler {

    public void hande(final ParseTraversal traversal) {
        preHandle(traversal);
        final Token currentToken = traversal.getCurrentToken();
        if (currentToken.isAlphaNumericCharacterToken()) {
            handleAlphaNumericCharacterToken(traversal);
        } else if (currentToken.isOtherTextualCharacterToken()) {
            handleOtherTextualCharacterToken(traversal);
        } else if (currentToken.isWhiteCharToken()) {
            handleWhiteCharacterToken(traversal);
        } else if (currentToken.isSentenceEndingToken()) {
            handleSentenceEndingToken(traversal);
        } else if (currentToken.isPhraseSeparatorToken()) {
            handlePhraseSeparatorToken(traversal);
        } else if (currentToken.isSoloQuotationToken()) {
            handleSoloQuotationToken(traversal);
        } else if (currentToken.isBlockToken()) {
            handleBlockToke(traversal);
        } else if (currentToken.isPairedQuotationToken()) {
            handlePairedQuotationToken(traversal);
        }
        postHandle(traversal);
    }

    private void preHandle(final ParseTraversal traversal) {
    }

    private void handleAlphaNumericCharacterToken(final ParseTraversal traversal) {
        traversal.setWhiteCharToken(null);
        appendCurrentAsAlphanumericToken(traversal);
    }

    private void handleOtherTextualCharacterToken(final ParseTraversal traversal) {
        traversal.setWhiteCharToken(null);
        finishParsingWord(traversal);
        appendToken(traversal);
    }

    private void handleWhiteCharacterToken(final ParseTraversal traversal) {
        if (traversal.haveNoWhiteCharTokenSet()) {
            traversal.setWhiteCharToken(traversal.getCurrentToken());
            finishParsingWord(traversal);
            appendToken(traversal);
        }
    }

    private void handleSentenceEndingToken(final ParseTraversal traversal) {
        traversal.setWhiteCharToken(null);
        finishParsingWord(traversal);
        appendToken(traversal);
    }

    private void handlePhraseSeparatorToken(final ParseTraversal traversal) {
        traversal.setWhiteCharToken(null);
        finishParsingWord(traversal);
        appendToken(traversal);
    }

    private void handleSoloQuotationToken(final ParseTraversal traversal) {
    }

    private void handleBlockToke(final ParseTraversal traversal) {
        traversal.setWhiteCharToken(null);
        if (traversal.getCurrentToken().isBeginBlockToken()) {
            handleBeginBlockToken(traversal);
        } else if (traversal.getCurrentToken().isEndBlockToken()) {
            handleEndBlockToken(traversal);
        }
        finishParsingWord(traversal);
        appendToken(traversal);
    }

    private void handleBeginBlockToken(final ParseTraversal traversal) {
    }

    private void handleEndBlockToken(final ParseTraversal traversal) {
    }

    private void handlePairedQuotationToken(final ParseTraversal traversal) {
        if (traversal.getCurrentToken().isBeginPairedQuotationToken()) {
            handleBeginPairedQuotationToken(traversal);
        } else if (traversal.getCurrentToken().isEndPairedQuotationToken()) {
            handleEndPairedQuotationToken(traversal);
        }
    }

    private void handleBeginPairedQuotationToken(final ParseTraversal traversal) {
    }

    private void handleEndPairedQuotationToken(final ParseTraversal traversal) {
    }

    private void postHandle(final ParseTraversal traversal) {
    }

    private void finishParsingWord(ParseTraversal traversal) {
        traversal.getParsingData().addWordTokenAndMakeNewCollector();
    }

    private void appendCurrentAsAlphanumericToken(final ParseTraversal traversal) {
        traversal.getParsingData().getAlphanumericsCollector().append(
            traversal.getCurrentToken().getValue()
        );
    }

    private void appendToken(final ParseTraversal traversal) {
        traversal.getParsingData().add(traversal.getCurrentToken());
    }
}
