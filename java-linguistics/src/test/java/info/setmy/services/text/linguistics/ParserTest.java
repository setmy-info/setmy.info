package info.setmy.services.text.linguistics;

import info.setmy.linguistics.Parser;
import info.setmy.linguistics.models.token.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParserTest {

    Parser parser;

    @BeforeEach
    public void before() {
        parser = new Parser();
    }

    @Test
    public void parsing() {
        final List<Token> parsedTokens = parser.parse("Tere");
        assertThat(parsedTokens).hasSize(1);
        assertThat(parsedTokens.get(0).toString()).isEqualTo("Tere");
    }
}
