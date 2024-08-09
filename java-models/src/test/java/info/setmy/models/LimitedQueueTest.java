package info.setmy.models;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class LimitedQueueTest {

    @Test
    public void add() {
        final LimitedQueue<Character> buffer = new LimitedQueue<>(5);
        buffer.add('a');
        assertThat(buffer.toArray().length).isEqualTo(1);
        assertThat(buffer.toArray()[0]).isEqualTo('a');
        buffer.add('b');
        assertThat(buffer.toArray().length).isEqualTo(2);
        assertThat(buffer.toArray()[0]).isEqualTo('a');
        assertThat(buffer.toArray()[1]).isEqualTo('b');
        buffer.add('c');
        buffer.add('d');
        buffer.add('e');
        assertThat(buffer.toArray().length).isEqualTo(5);
        assertThat(buffer.toArray()[0]).isEqualTo('a');
        assertThat(buffer.toArray()[1]).isEqualTo('b');
        assertThat(buffer.toArray()[2]).isEqualTo('c');
        assertThat(buffer.toArray()[3]).isEqualTo('d');
        assertThat(buffer.toArray()[4]).isEqualTo('e');
    }
}
