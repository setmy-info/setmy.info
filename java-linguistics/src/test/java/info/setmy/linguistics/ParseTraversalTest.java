package info.setmy.linguistics;

import org.junit.jupiter.api.BeforeEach;

public class ParseTraversalTest {

    ParseTraversal parseTraversal;
    char[] characters;

    @BeforeEach
    public void before() {
        parseTraversal = new ParseTraversal("Hello World");
    }
}
