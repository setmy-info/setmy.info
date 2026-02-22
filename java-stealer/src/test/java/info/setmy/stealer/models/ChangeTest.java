package info.setmy.stealer.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChangeTest {

    @Test
    void builder_shouldCreateChange() {
        final Change change = Change.builder()
            .pattern("old value")
            .replacement("new value")
            .build();

        assertThat(change.getPattern()).isEqualTo("old value");
        assertThat(change.getReplacement()).isEqualTo("new value");
    }

    @Test
    void toBuilder_shouldProduceModifiedCopy() {
        final Change original = Change.builder()
            .pattern("old")
            .replacement("new")
            .build();

        final Change modified = original.toBuilder()
            .replacement("replaced")
            .build();

        assertThat(modified.getPattern()).isEqualTo("old");
        assertThat(modified.getReplacement()).isEqualTo("replaced");
        assertThat(original.getReplacement()).isEqualTo("new");
    }
}
