package info.setmy.services.text.linguistics;

import info.setmy.services.text.linguistics.model.TextItem;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static info.setmy.models.FileRows.newFileRows;

/**
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class SentenceParserTest {

    SentenceParser sentenceParser;
    List<String> sentences;

    @Before
    public void setUp() {
        sentenceParser = new SentenceParser();
        sentences = newFileRows("./src/test/resources/sentences/sentences.txt").get().getRows();
    }

    @Test
    public void abc() {
        /*assertThat(sentenceParser.getTextSeparatorType('.')).isEqualTo(TextSeparatorType.SENTENCE_SEPARATOR); // end and start new sentence, clause, block, workd if any.
        assertThat(sentenceParser.getTextSeparatorType(',')).isEqualTo(TextSeparatorType.CLAUSE_SEPARATOR);
        assertThat(sentenceParser.getTextSeparatorType('(')).isEqualTo(TextSeparatorType.BLOCK_SEPARATOR_BEGINNING);
        assertThat(sentenceParser.getTextSeparatorType(')')).isEqualTo(TextSeparatorType.BLOCK_SEPARATOR_END);
        assertThat(sentenceParser.getTextSeparatorType('\'')).isEqualTo(TextSeparatorType.QUOTE_BLOCK_CLAUSE_SEPARATOR);*/
    }

    @Test
    public void parsing1() {
        /*final String text = "This is longer sentence, with some sentence ending characters. Maybe text have questions? Maybe text ends with!";
        sentenceParser.parse(text);
        assertThat(sentenceParser.getSentences().size()).isEqualTo(3);
        assertThat(sentenceParser.getSentences().get(0).getEndingCharacter()).isEqualTo('.');
        assertThat(sentenceParser.getSentences().get(1).getEndingCharacter()).isEqualTo('?');
        assertThat(sentenceParser.getSentences().get(2).getEndingCharacter()).isEqualTo('!');*/
    }

    @Test
    public void parse2() {
        sentenceParser.parse("Aaa, bbb (ccc, ddd, eee), fff (ggg; hhh; iii), jjj - kkk, lll mmm nnn: \"ooo, ppp, rrr sss - ttt (uuu; vvv õõõ, äää ööö!)\". Üüü (xxx, yyy, zzz [aaa, bbb, ccc {ddd, eee, fff ggg hhh}])! Iii, jjj, kkk: lll, mmm nnn? Ooo: \"ppp rrr sss?\"");
        final List<TextItem> sentences = sentenceParser.getSentences();
        //assertThat(sentences.size()).isEqualTo(2);
    }
}
