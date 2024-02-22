package info.setmy.linguistics;

import info.setmy.linguistics.models.token.EventType;

import java.util.ArrayList;
import java.util.List;

import static info.setmy.linguistics.models.token.EventType.ALPHA_NUMERIC_TO_ANY;
import static info.setmy.linguistics.models.token.EventType.ANY_TO_ALPHA_NUMERIC;
import static info.setmy.linguistics.models.token.EventType.CLASS_TYPE_CHANGE;
import static info.setmy.linguistics.models.token.EventType.NULL_TO_ANY;

public class EventDecider {

    public List<EventType> decide(final ParseTraversal traversal) {
        final List<EventType> result = new ArrayList<>();
        if (isNullToAnyChange(traversal)) {
            result.add(NULL_TO_ANY);
        }
        if (isClassTypeChange(traversal)) {
            result.add(CLASS_TYPE_CHANGE);
        }
        if (isAlphaNumericToAny(traversal)) {
            result.add(ALPHA_NUMERIC_TO_ANY);
        }
        if (isAnyToAlphaNumericChange(traversal)) {
            result.add(ANY_TO_ALPHA_NUMERIC);
        }
        return result;
    }

    boolean isNullToAnyChange(final ParseTraversal traversal) {
        return traversal.getPreviousToken() == null && traversal.getCurrentToken() != null;
    }

    boolean isClassTypeChange(final ParseTraversal traversal) {
        return traversal.getPreviousToken().getClass() != traversal.getCurrentToken().getClass();
    }

    boolean isAlphaNumericToAny(final ParseTraversal traversal) {
        if (traversal.getPreviousToken() == null) {
            return false;
        }
        return traversal.getPreviousToken().isAlphanumericCharacterToken() && !traversal.getCurrentToken().isAlphanumericCharacterToken();
    }

    boolean isAnyToAlphaNumericChange(final ParseTraversal traversal) {
        if (traversal.getPreviousToken() == null) {
            return false;
        }
        return !traversal.getPreviousToken().isAlphanumericCharacterToken() && traversal.getCurrentToken().isAlphanumericCharacterToken();
    }
}
