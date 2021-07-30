package info.setmy.stealer.models;

import java.io.File;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class StealerTest {

    private Stealer stealer;

    private final String WORKING_DIR = "./";

    @BeforeEach
    public void before() {
        stealer = Stealer.builder()
                .workingDirectory(WORKING_DIR)
                .build();
    }

    @Test
    public void getClonesDir() throws IOException {
        final File directory = stealer.getClonesDir();
        assertThat(directory.getAbsolutePath()).endsWith("/setmy.info/java-stealer/./.stealer/clones");
    }

    @Test
    @DisplayName("working directory is left null")
    public void getClonesDir_empty() throws IOException {
        stealer = Stealer.builder()
                .build();

        final File directory = stealer.getClonesDir();

        assertThat(directory.getAbsolutePath()).endsWith("/setmy.info/java-stealer/.stealer/clones");
    }

    @Test
    @DisplayName("working directory is set to something")
    public void getClonesDir_something() throws IOException {
        stealer = Stealer.builder()
                .workingDirectory("/some/dir")
                .build();

        final File directory = stealer.getClonesDir();

        assertThat(directory.getAbsolutePath()).isEqualTo("/some/dir/.stealer/clones");
    }
}
