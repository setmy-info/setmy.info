package info.setmy.stealer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.apache.commons.lang3.SystemUtils.IS_OS_WINDOWS;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class StealerTest {

    private Stealer stealer;

    private final String WORKING_DIR = "./";

    @BeforeEach
    public void before() {
        stealer = new Stealer(null, WORKING_DIR);
    }

    @Test
    public void getClonesDir() {
        final File directory = stealer.getClonesDir();
        assertThat(directory.getAbsolutePath()).endsWith(byOS("/java-stealer/./.stealer/clones"));
    }

    @Test
    @DisplayName("working directory is left null")
    public void getClonesDir_empty() {
        stealer = new Stealer(null, null);

        final File directory = stealer.getClonesDir();

        assertThat(directory.getAbsolutePath()).endsWith(byOS("/java-stealer/.stealer/clones"));
    }

    @Test
    @DisplayName("working directory is set to something")
    public void getClonesDir_something() {
        stealer = new Stealer(null, "/some/dir");

        final File directory = stealer.getClonesDir();

        if (IS_OS_WINDOWS) {
            assertThat(directory.getAbsolutePath()).isEqualTo("C:\\some\\dir\\.stealer\\clones");
        } else {
            assertThat(directory.getAbsolutePath()).isEqualTo("/some/dir/.stealer/clones");
        }
    }

    private String byOS(final String inputString) {
        return IS_OS_WINDOWS ? inputString.replace("/", "\\") : inputString;
    }
}
