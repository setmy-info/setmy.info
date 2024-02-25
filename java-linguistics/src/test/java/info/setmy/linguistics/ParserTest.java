package info.setmy.linguistics;

import info.setmy.linguistics.models.token.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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

    @Test
    public void manySentences() {
        final String string = "This is longer sentence, with some sentence ending characters. Maybe text have questions? Maybe text ends with!";
        final List<Token> parsedTokens = parser.parse(string);
        assertThat(parsedTokens).hasSize(21);
        assertThat(parsedTokens.get(0).toString()).isEqualTo("This");
        assertThat(parsedTokens.get(1).toString()).isEqualTo("is");
        assertThat(parsedTokens.get(2).toString()).isEqualTo("longer");
        assertThat(parsedTokens.get(3).toString()).isEqualTo("sentence");
        assertThat(parsedTokens.get(4).toString()).isEqualTo(",");
        assertThat(parsedTokens.get(5).toString()).isEqualTo("with");
        assertThat(parsedTokens.get(6).toString()).isEqualTo("some");
        assertThat(parsedTokens.get(7).toString()).isEqualTo("sentence");
        assertThat(parsedTokens.get(8).toString()).isEqualTo("ending");
        assertThat(parsedTokens.get(9).toString()).isEqualTo("characters");
        assertThat(parsedTokens.get(10).toString()).isEqualTo(".");
        assertThat(parsedTokens.get(11).toString()).isEqualTo("Maybe");
        assertThat(parsedTokens.get(12).toString()).isEqualTo("text");
        assertThat(parsedTokens.get(13).toString()).isEqualTo("have");
        assertThat(parsedTokens.get(14).toString()).isEqualTo("questions");
        assertThat(parsedTokens.get(15).toString()).isEqualTo("?");
        assertThat(parsedTokens.get(16).toString()).isEqualTo("Maybe");
        assertThat(parsedTokens.get(17).toString()).isEqualTo("text");
        assertThat(parsedTokens.get(18).toString()).isEqualTo("ends");
        assertThat(parsedTokens.get(19).toString()).isEqualTo("with");
        assertThat(parsedTokens.get(20).toString()).isEqualTo("!");
    }

    @Test
    public void manySentences2() {
        final String string = "Kesk-Aasia elanik";
        final List<Token> parsedTokens = parser.parse(string);
        assertThat(parsedTokens).hasSize(2);
        assertThat(parsedTokens.get(0).toString()).isEqualTo("Kesk-Aasia");
        assertThat(parsedTokens.get(1).toString()).isEqualTo("elanik");
    }

    @Test
    @Disabled // TODO : parsing () etc character correctly into words or separate tokens
    public void manySentences3() {
        final String string = "tulekahju avastanud isik(ud), teis(t)ele võimalus(t)ele, ega ta (ei) tule.";
        final List<Token> parsedTokens = parser.parse(string);
        assertThat(parsedTokens).hasSize(12);
        assertThat(parsedTokens.get(0).toString()).isEqualTo("tulekahju");
        assertThat(parsedTokens.get(1).toString()).isEqualTo("avastanud");
        assertThat(parsedTokens.get(2).toString()).isEqualTo("isik(ud)");
        assertThat(parsedTokens.get(3).toString()).isEqualTo(",");
        assertThat(parsedTokens.get(4).toString()).isEqualTo("teis(t)ele");
        assertThat(parsedTokens.get(5).toString()).isEqualTo("võimalus(t)ele");
        assertThat(parsedTokens.get(6).toString()).isEqualTo(",");
        assertThat(parsedTokens.get(7).toString()).isEqualTo("ega");
        assertThat(parsedTokens.get(8).toString()).isEqualTo("ta");
        assertThat(parsedTokens.get(9).toString()).isEqualTo("(ei)");
        assertThat(parsedTokens.get(10).toString()).isEqualTo("tule");
        assertThat(parsedTokens.get(11).toString()).isEqualTo(".");
    }

    @Test
    @Disabled // TODO : parsing () etc character correctly into words or separate tokens
    public void manySentences4() {
        final String string = "2) seletavad ja täiendavad kiilmärkused, nt sõnaseletused, viited, nimede hääldus: brokoli (spargelkapsas); sajab kõikjal (välja arvatud saartel ~ välja arvatud saared); elamute (Pärnu, Sireli 17 ja 17a) lammutamine; Tambet Lepik (edaspidi: töövõtja); Ita (õieti Ilse) Ever; Nike (loe: naiki) jalatsid;";
        final List<Token> parsedTokens = parser.parse(string);
        assertThat(parsedTokens).hasSize(12);
        assertThat(parsedTokens.get(0).toString()).isEqualTo("xxx");
    }

    @Test
    @Disabled // TODO : make string correct by estonian language rules
    public void manySentencesXXX() {
        final String string = "Aaa, bbb (ccc, ddd, eee), fff (ggg; hhh; iii), jjj - kkk, lll mmm nnn: \"ooo, ppp, rrr sss - ttt (uuu; vvv õõõ, äää ööö!)\". Üüü (xxx, yyy, zzz [aaa, bbb, ccc {ddd, eee, fff ggg hhh}])! Iii, jjj, kkk: lll, mmm nnn? Ooo: \"ppp rrr sss?\"";
        final List<Token> parsedTokens = parser.parse(string);
        assertThat(parsedTokens).hasSize(21);
        assertThat(parsedTokens.get(0).toString()).isEqualTo("This");
    }

    @Test
    @Disabled
    // TODO : decide, should it be word or separate tokens. Most probably one single word token, because it is continuous characters and should be handled in post processing/oparsing.
    public void onlySentenceEndings() {
        final List<Token> parsedTokens = parser.parse(".!?");
        assertThat(parsedTokens).hasSize(3);
        assertThat(parsedTokens.get(0).toString()).isEqualTo(".");
        assertThat(parsedTokens.get(1).toString()).isEqualTo("!");
        assertThat(parsedTokens.get(2).toString()).isEqualTo("?");
    }
}
