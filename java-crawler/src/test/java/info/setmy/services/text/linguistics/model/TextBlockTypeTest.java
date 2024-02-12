package info.setmy.services.text.linguistics.model;

import static info.setmy.services.text.linguistics.model.TextBlockType.UNKNOWN;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;

public class TextBlockTypeTest {

    TextBlockType word;
    TextBlockType sentence;
    TextBlockType block;
    TextBlockType quote;
    TextBlockType clause;

    @Before
    public void setUp() {
        word = TextBlockType.WORD;
        sentence = TextBlockType.SENTENCE;
        block = TextBlockType.BLOCK;
        quote = TextBlockType.QUOTE;
        clause = TextBlockType.CLAUSE;
    }

    @Test
    public void word() {
        assertThat(word.is(3)).isEqualTo(true);
        assertThat(word.is(1000)).isEqualTo(false);
    }

    @Test
    public void sentence() {
        assertThat(sentence.is(1300)).isEqualTo(true);
        assertThat(sentence.is(2000)).isEqualTo(false);
    }

    @Test
    public void block() {
        assertThat(block.is(2300)).isEqualTo(true);
        assertThat(block.is(3000)).isEqualTo(false);
    }

    @Test
    public void quote() {
        assertThat(quote.is(3300)).isEqualTo(true);
        assertThat(quote.is(4000)).isEqualTo(false);
    }

    @Test
    public void clause() {
        assertThat(clause.is(4300)).isEqualTo(true);
        assertThat(clause.is(5000)).isEqualTo(false);
    }

    @Test
    public void unknown() {
        final TextBlockType any = word;//Just any
        assertThat(any.toSimplify(5000)).isEqualTo(UNKNOWN);
    }
}
