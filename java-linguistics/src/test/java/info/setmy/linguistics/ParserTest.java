package info.setmy.linguistics;

import info.setmy.linguistics.models.token.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParserTest {

    Parser parser;

    @BeforeEach
    public void before() {
        parser = new Parser();
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "Hello",
        "  Hello  ",
        "Hello  ",
        "  Hello"}
    )
    public void parsingSingleWord(final String string) {
        final List<Token> parsedTokens = parser.parse(string);
        assertThat(parsedTokens).hasSize(1);
        assertThat(parsedTokens.get(0).toString()).isEqualTo("Hello");
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "Hello World",
        "  Hello   World "
    })
    public void parsingTwo(final String string) {
        final List<Token> parsedTokens = parser.parse(string);
        assertThat(parsedTokens).hasSize(2);
        assertThat(parsedTokens.get(0).toString()).isEqualTo("Hello");
        assertThat(parsedTokens.get(1).toString()).isEqualTo("World");
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "https://example.com/url/atr?abs=123%2C",
        "  https://example.com/url/atr?abs=123%2C "
    })
    public void parsingUrlAsComplexWordStructure(final String string) {
        final List<Token> parsedTokens = parser.parse(string);
        assertThat(parsedTokens).hasSize(1);
        assertThat(parsedTokens.get(0).toString()).isEqualTo("https://example.com/url/atr?abs=123%2C");
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "https://example.com/url/atr?abs=123,",
        "  https://example.com/url/atr?abs=123, "
    })
    public void parsingUrlAsComplexWordStructureWithEndingComma(final String string) {
        final List<Token> parsedTokens = parser.parse(string);
        assertThat(parsedTokens).hasSize(2);
        assertThat(parsedTokens.get(0).toString()).isEqualTo("https://example.com/url/atr?abs=123");
        assertThat(parsedTokens.get(1).toString()).isEqualTo(",");
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "Hello, World",
        "  Hello,  World  ",
        "  Hello  ,  World  "
    })
    public void commaSeparated(final String string) {
        final List<Token> parsedTokens = parser.parse(string);
        assertThat(parsedTokens).hasSize(3);
        assertThat(parsedTokens.get(0).toString()).isEqualTo("Hello");
        assertThat(parsedTokens.get(1).toString()).isEqualTo(",");
        assertThat(parsedTokens.get(2).toString()).isEqualTo("World");
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "Hello, World.",
        "   Hello  ,   World   .  "
    })
    public void commaSeparatedNormalSentence(final String string) {
        final List<Token> parsedTokens = parser.parse(string);
        assertThat(parsedTokens).hasSize(4);
        assertThat(parsedTokens.get(0).toString()).isEqualTo("Hello");
        assertThat(parsedTokens.get(1).toString()).isEqualTo(",");
        assertThat(parsedTokens.get(2).toString()).isEqualTo("World");
        assertThat(parsedTokens.get(3).toString()).isEqualTo(".");
    }

    @Test
    public void commaSeparatedNormalSentenceUnStriped() {
        final List<Token> parsedTokens = parser.parse("   Hello,    World.   ");
        assertThat(parsedTokens).hasSize(4);
        assertThat(parsedTokens.get(0).toString()).isEqualTo("Hello");
        assertThat(parsedTokens.get(1).toString()).isEqualTo(",");
        assertThat(parsedTokens.get(2).toString()).isEqualTo("World");
        assertThat(parsedTokens.get(3).toString()).isEqualTo(".");
    }

    @Test
    public void numberedSentenceCorrect() {
        final List<Token> parsedTokens = parser.parse("11.23% of world population.");
        assertThat(parsedTokens).hasSize(5);
        assertThat(parsedTokens.get(0).toString()).isEqualTo("11.23%");
        assertThat(parsedTokens.get(1).toString()).isEqualTo("of");
        assertThat(parsedTokens.get(2).toString()).isEqualTo("world");
        assertThat(parsedTokens.get(3).toString()).isEqualTo("population");
        assertThat(parsedTokens.get(4).toString()).isEqualTo(".");
    }

    @Test
    public void numberedSentence() {
        final List<Token> parsedTokens = parser.parse("   10,11 % of world population.   ");
        assertThat(parsedTokens).hasSize(6);
        assertThat(parsedTokens.get(0).toString()).isEqualTo("10,11");
        assertThat(parsedTokens.get(1).toString()).isEqualTo("%");
        assertThat(parsedTokens.get(2).toString()).isEqualTo("of");
        assertThat(parsedTokens.get(3).toString()).isEqualTo("world");
        assertThat(parsedTokens.get(4).toString()).isEqualTo("population");
        assertThat(parsedTokens.get(5).toString()).isEqualTo(".");
    }

    /*@Test
    public void onlySentenceEndings() {
        final List<Token> parsedTokens = parser.parse(".!?");
        assertThat(parsedTokens).hasSize(3);
        assertThat(parsedTokens.get(0).toString()).isEqualTo(".");
        assertThat(parsedTokens.get(1).toString()).isEqualTo("!");
        assertThat(parsedTokens.get(2).toString()).isEqualTo("?");
    }*/
}
