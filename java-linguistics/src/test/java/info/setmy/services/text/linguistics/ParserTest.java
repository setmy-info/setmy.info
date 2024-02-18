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
    public void parsingSingle() {
        final List<Token> parsedTokens = parser.parse("Tere");
        assertThat(parsedTokens).hasSize(1);
        assertThat(parsedTokens.get(0).toString()).isEqualTo("Tere");
    }

    @Test
    public void parsingSingleWithWhiteExclusion() {
        final List<Token> parsedTokens = parser.parse("  Tere  ");
        assertThat(parsedTokens).hasSize(1);
        assertThat(parsedTokens.get(0).toString()).isEqualTo("Tere");
    }

    @Test
    public void parsingTwo() {
        final List<Token> parsedTokens = parser.parse("Tere maailm");
        assertThat(parsedTokens).hasSize(2);
        assertThat(parsedTokens.get(0).toString()).isEqualTo("Tere");
        assertThat(parsedTokens.get(1).toString()).isEqualTo("maailm");
    }

    @Test
    public void parsingTwoWithWhiteExclusion() {
        final List<Token> parsedTokens = parser.parse("  Tere   maailm ");
        assertThat(parsedTokens).hasSize(2);
        assertThat(parsedTokens.get(0).toString()).isEqualTo("Tere");
        assertThat(parsedTokens.get(1).toString()).isEqualTo("maailm");
    }
}
