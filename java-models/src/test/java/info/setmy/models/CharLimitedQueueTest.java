package info.setmy.models;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class CharLimitedQueueTest {

    CharLimitedQueue buffer;

    @BeforeEach
    public void before() {
        buffer = new CharLimitedQueue(5);
    }

    @Test
    public void add() {
        assertThat(buffer.getRemainingCapacity(), is(equalTo(5)));
        buffer.add('a');
        assertThat(buffer.getRemainingCapacity(), is(equalTo(4)));
        assertThat(buffer.getArray()[0], is(equalTo('a')));
        assertThat(buffer.toString(), is(equalTo("a")));
        buffer.add('b');
        assertThat(buffer.getRemainingCapacity(), is(equalTo(3)));
        assertThat(buffer.getArray()[0], is(equalTo('a')));
        assertThat(buffer.getArray()[1], is(equalTo('b')));
        assertThat(buffer.toString(), is(equalTo("ab")));
        buffer.add('c');
        buffer.add('d');
        buffer.add('e');
        assertThat(buffer.getRemainingCapacity(), is(equalTo(0)));
        assertThat(buffer.getArray()[0], is(equalTo('a')));
        assertThat(buffer.getArray()[1], is(equalTo('b')));
        assertThat(buffer.getArray()[2], is(equalTo('c')));
        assertThat(buffer.getArray()[3], is(equalTo('d')));
        assertThat(buffer.getArray()[4], is(equalTo('e')));
        assertThat(buffer.toString(), is(equalTo("abcde")));
        buffer.add('f');
        assertThat(buffer.getRemainingCapacity(), is(equalTo(0)));
        assertThat(buffer.getArray()[0], is(equalTo('b')));
        assertThat(buffer.getArray()[1], is(equalTo('c')));
        assertThat(buffer.getArray()[2], is(equalTo('d')));
        assertThat(buffer.getArray()[3], is(equalTo('e')));
        assertThat(buffer.getArray()[4], is(equalTo('f')));
        assertThat(buffer.toString(), is(equalTo("bcdef")));
    }

    @Test
    public void clear() {
        add();
        buffer.clear();
        add();
    }
}
