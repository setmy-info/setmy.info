package info.setmy.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class TripleCursorTest {

    TripleCursor<String> tripleCursor;

    @Test
    public void noElements() {
        tripleCursor = new TripleCursor(new ArrayList());
        int i = 0;
        while (tripleCursor.haveCurrent()) {
            i++;
            tripleCursor.next();
        }
        assertThat(i).isEqualTo(0);
    }

    @Test
    public void oneElement() {
        tripleCursor = new TripleCursor(asList("abc"));
        String result = "";
        int i = 0;
        while (tripleCursor.haveCurrent()) {
            i++;
            result += tripleCursor.getOptionalCurrent().orElse("");
            tripleCursor.next();
        }
        assertThat(i).isEqualTo(1);
        assertThat(result).isEqualTo("abc");
    }

    @Test
    public void twoElements() {
        tripleCursor = new TripleCursor(asList("abc", "def"));
        String result = "";
        int i = 0;
        while (tripleCursor.haveCurrent()) {
            i++;
            result += tripleCursor.getOptionalCurrent().orElse("");
            tripleCursor.next();
        }
        assertThat(i).isEqualTo(2);
        assertThat(result).isEqualTo("abcdef");
    }
}
