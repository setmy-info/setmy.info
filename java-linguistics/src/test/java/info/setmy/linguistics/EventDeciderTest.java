package info.setmy.linguistics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static info.setmy.linguistics.models.token.TokenUtils.toToken;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventDeciderTest {

    EventDecider eventDecider;
    ParseTraversal traversal;
    final String TEXT = "abc";

    @BeforeEach
    public void before() {
        traversal = new ParseTraversal(TEXT);
        eventDecider = new EventDecider();
    }
}
