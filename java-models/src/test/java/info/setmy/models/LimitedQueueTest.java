package info.setmy.models;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class LimitedQueueTest {

    @Test
    public void add() {
        final LimitedQueue<Character> buffer = new LimitedQueue<>(5);
        buffer.add('a');
        assertThat(buffer.toArray().length, is(equalTo(1)));
        assertThat(buffer.toArray()[0], is(equalTo('a')));
        buffer.add('b');
        assertThat(buffer.toArray().length, is(equalTo(2)));
        assertThat(buffer.toArray()[0], is(equalTo('a')));
        assertThat(buffer.toArray()[1], is(equalTo('b')));
        buffer.add('c');
        buffer.add('d');
        buffer.add('e');
        assertThat(buffer.toArray().length, is(equalTo(5)));
        assertThat(buffer.toArray()[0], is(equalTo('a')));
        assertThat(buffer.toArray()[1], is(equalTo('b')));
        assertThat(buffer.toArray()[2], is(equalTo('c')));
        assertThat(buffer.toArray()[3], is(equalTo('d')));
        assertThat(buffer.toArray()[4], is(equalTo('e')));
    }
}
