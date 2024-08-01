package info.setmy.models;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class PairTest {

    @Test
    public void equals() {
        Pair<String, String> one = new Pair("A", "B");
        Pair<String, String> second = new Pair("A", "B");
        assertThat(one.equals(second)).isTrue();
    }

    @Test
    public void equalsForDifferentPairTypes() {
        Pair<String, String> one = new Pair("A", "123");
        Pair<String, Integer> second = new Pair("A", 123);
        assertThat(one.equals(second)).isFalse();
    }

    @Test
    public void equalsForNullSecond() {
        Pair<String, String> one = new Pair("A", "123");
        assertThat(one.equals(null)).isFalse();
    }

    @Test
    public void equalsForDifferentSecondType() {
        Pair<String, String> one = new Pair("A", "123");
        assertThat(one.equals("Hello World")).isFalse();
    }
}
