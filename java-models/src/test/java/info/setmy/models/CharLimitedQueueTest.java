package info.setmy.models;

import static org.assertj.core.api.Assertions.assertThat;
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
        assertThat(buffer.getRemainingCapacity()).isEqualTo(5);
        buffer.add('a');
        assertThat(buffer.getRemainingCapacity()).isEqualTo(4);
        assertThat(buffer.getArray()[0]).isEqualTo('a');
        assertThat(buffer.toString()).isEqualTo("a");
        buffer.add('b');
        assertThat(buffer.getRemainingCapacity()).isEqualTo(3);
        assertThat(buffer.getArray()[0]).isEqualTo('a');
        assertThat(buffer.getArray()[1]).isEqualTo('b');
        assertThat(buffer.toString()).isEqualTo("ab");
        buffer.add('c');
        buffer.add('d');
        buffer.add('e');
        assertThat(buffer.getRemainingCapacity()).isEqualTo(0);
        assertThat(buffer.getArray()[0]).isEqualTo('a');
        assertThat(buffer.getArray()[1]).isEqualTo('b');
        assertThat(buffer.getArray()[2]).isEqualTo('c');
        assertThat(buffer.getArray()[3]).isEqualTo('d');
        assertThat(buffer.getArray()[4]).isEqualTo('e');
        assertThat(buffer.toString()).isEqualTo("abcde");
        buffer.add('f');
        assertThat(buffer.getRemainingCapacity()).isEqualTo(0);
        assertThat(buffer.getArray()[0]).isEqualTo('b');
        assertThat(buffer.getArray()[1]).isEqualTo('c');
        assertThat(buffer.getArray()[2]).isEqualTo('d');
        assertThat(buffer.getArray()[3]).isEqualTo('e');
        assertThat(buffer.getArray()[4]).isEqualTo('f');
        assertThat(buffer.toString()).isEqualTo("bcdef");
    }

    @Test
    public void clear() {
        add();
        buffer.clear();
        add();
    }
}
