package info.setmy.services.text.linguistics;

import info.setmy.linguistics.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParserTest {

    Parser parser;

    @BeforeEach
    public void before() {
        parser = new Parser();
    }

    @Test
    public void parsing() {
        parser.parse("Tere");
    }
}
