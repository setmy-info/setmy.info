package info.setmy.examples.lessons.level6;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class JUnit5Test {

    @Test
    public void justAnExample() {
        boolean result = foo();
        assumeFalse(result);
        assertThat(result).isEqualTo(false);
    }

    private boolean foo() {
        return true;
    }
}
