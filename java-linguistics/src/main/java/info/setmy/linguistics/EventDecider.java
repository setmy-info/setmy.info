package info.setmy.linguistics;

import info.setmy.linguistics.models.token.EventType;
import static info.setmy.linguistics.models.token.EventType.END_WORD;
import static info.setmy.linguistics.models.token.EventType.START_WORD;

import java.util.ArrayList;
import java.util.List;

public class EventDecider {

    public List<EventType> decide(final ParseTraversal traversal) {
        final List<EventType> result = new ArrayList<>();
        wordStarting(traversal, result);
        wordEnding(traversal, result);
        return result;
    }

    private void wordStarting(final ParseTraversal traversal, final List<EventType> result) {
        if ((traversal.haveNoPreviousToken() || traversal.getPreviousToken().isWhiteCharSingleToken())
                && traversal.getCurrentToken().isNotWhiteCharSingleToken()) { // Current is any non white
            result.add(START_WORD);
        }
    }

    private void wordEnding(final ParseTraversal traversal, final List<EventType> result) {
        if ((traversal.havePreviousToken()
                && traversal.getPreviousToken().isNotWhiteCharSingleToken() // Prev token is any non white
                && traversal.getCurrentToken().isWhiteCharSingleToken())
                || (traversal.getCurrentToken().isAlphaNumericCharacterToken() && traversal.getIndex() == traversal.getCharacters().length - 1)) { // aplhanumeric is last character in char array
            result.add(END_WORD);
        }
    }
}
