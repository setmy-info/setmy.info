package info.setmy.services.text.linguistics;

import info.setmy.linguistics.ParseTraversal;
import org.junit.jupiter.api.BeforeEach;

public class ParseTraversalTest {

    ParseTraversal parseTraversal;

    @BeforeEach
    public void before() {
        parseTraversal = new ParseTraversal();
    }

    public void isTextToNonTextChange() {
        parseTraversal.isTextToNonTextChange();
    }
}
