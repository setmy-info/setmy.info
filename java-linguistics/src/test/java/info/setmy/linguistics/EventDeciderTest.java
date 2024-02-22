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

    @Test
    public void isNullToAnyChange() {
        assertTrue(eventDecider.isNullToAnyChange(
            traversal
                .setPreviousToken(null)
                .setCurrentToken(toToken('a'))
        ));
        assertTrue(eventDecider.isNullToAnyChange(
            traversal
                .setPreviousToken(null)
                .setCurrentToken(toToken('0'))
        ));
        assertTrue(eventDecider.isNullToAnyChange(
            traversal
                .setPreviousToken(null)
                .setCurrentToken(toToken(':'))
        ));
        assertTrue(eventDecider.isNullToAnyChange(
            traversal
                .setPreviousToken(null)
                .setCurrentToken(toToken(' '))
        ));
        assertFalse(eventDecider.isNullToAnyChange(
            traversal
                .setPreviousToken(toToken(' '))
                .setCurrentToken(toToken(' '))
        ));
        assertFalse(eventDecider.isNullToAnyChange(
            traversal
                .setPreviousToken(toToken('a'))
                .setCurrentToken(toToken(':'))
        ));
        assertFalse(eventDecider.isNullToAnyChange(
            traversal
                .setPreviousToken(toToken('1'))
                .setCurrentToken(toToken(':'))
        ));
        assertFalse(eventDecider.isNullToAnyChange(
            traversal
                .setPreviousToken(toToken(':'))
                .setCurrentToken(toToken('a'))
        ));
    }

    @Test
    public void isAlphaNumericToAny() {
        assertTrue(eventDecider.isAlphaNumericToAny(traversal
            .setPreviousToken(toToken('a'))
            .setCurrentToken(toToken(':'))));
        assertTrue(eventDecider.isAlphaNumericToAny(traversal
            .setPreviousToken(toToken('0'))
            .setCurrentToken(toToken(':'))));
        assertTrue(eventDecider.isAlphaNumericToAny(traversal
            .setPreviousToken(toToken('0'))
            .setCurrentToken(toToken(' '))));
        assertFalse(eventDecider.isAlphaNumericToAny(traversal
            .setPreviousToken(toToken('0'))
            .setCurrentToken(toToken('0'))));
        assertFalse(eventDecider.isAlphaNumericToAny(traversal
            .setPreviousToken(toToken('a'))
            .setCurrentToken(toToken('a'))));
        assertFalse(eventDecider.isAlphaNumericToAny(traversal
            .setPreviousToken(toToken(' '))
            .setCurrentToken(toToken(' '))));
    }

    @Test
    public void isClassTypeChange() {
        assertTrue(eventDecider.isClassTypeChange(traversal
            .setPreviousToken(toToken('a'))
            .setCurrentToken(toToken(':'))));
        assertTrue(eventDecider.isClassTypeChange(traversal
            .setPreviousToken(toToken('a'))
            .setCurrentToken(toToken(' '))));
        assertTrue(eventDecider.isClassTypeChange(traversal
            .setPreviousToken(toToken('a'))
            .setCurrentToken(toToken('1'))));
        assertFalse(eventDecider.isClassTypeChange(traversal
            .setPreviousToken(toToken('a'))
            .setCurrentToken(toToken('a'))));
    }

    @Test
    public void isAnyToAlphaNumericChange() {
        assertTrue(eventDecider.isAnyToAlphaNumericChange(traversal
            .setPreviousToken(toToken(' '))
            .setCurrentToken(toToken('a'))));
        assertTrue(eventDecider.isAnyToAlphaNumericChange(traversal
            .setPreviousToken(toToken(' '))
            .setCurrentToken(toToken('0'))));
        assertTrue(eventDecider.isAnyToAlphaNumericChange(traversal
            .setPreviousToken(toToken(':'))
            .setCurrentToken(toToken('a'))));
        assertTrue(eventDecider.isAnyToAlphaNumericChange(traversal
            .setPreviousToken(toToken(':'))
            .setCurrentToken(toToken('0'))));
        assertFalse(eventDecider.isAnyToAlphaNumericChange(traversal
            .setPreviousToken(toToken('a'))
            .setCurrentToken(toToken('a'))));
        assertFalse(eventDecider.isAnyToAlphaNumericChange(traversal
            .setPreviousToken(toToken('a'))
            .setCurrentToken(toToken('0'))));
        assertFalse(eventDecider.isAnyToAlphaNumericChange(traversal
            .setPreviousToken(toToken('0'))
            .setCurrentToken(toToken('a'))));
    }
}
