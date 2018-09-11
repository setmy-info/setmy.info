package info.setmy.services.text.linguistics.model;

import info.setmy.services.text.linguistics.model.TextBlockType;
import static info.setmy.services.text.linguistics.model.TextBlockType.UNKNOWN;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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
        assertThat(word.is(3), is(equalTo(true)));
        assertThat(word.is(1000), is(equalTo(false)));
    }

    @Test
    public void sentence() {
        assertThat(sentence.is(1300), is(equalTo(true)));
        assertThat(sentence.is(2000), is(equalTo(false)));
    }

    @Test
    public void block() {
        assertThat(block.is(2300), is(equalTo(true)));
        assertThat(block.is(3000), is(equalTo(false)));
    }

    @Test
    public void quote() {
        assertThat(quote.is(3300), is(equalTo(true)));
        assertThat(quote.is(4000), is(equalTo(false)));
    }

    @Test
    public void clause() {
        assertThat(clause.is(4300), is(equalTo(true)));
        assertThat(clause.is(5000), is(equalTo(false)));
    }

    @Test
    public void unknown() {
        final TextBlockType any = word;//Just any
        assertThat(any.toSimplify(5000), is(equalTo(UNKNOWN)));
    }
}
