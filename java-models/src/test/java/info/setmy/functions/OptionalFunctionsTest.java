package info.setmy.functions;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static info.setmy.functions.OptionalFunctions.blankAsNull;
import static info.setmy.functions.OptionalFunctions.blankToEmpty;
import static info.setmy.functions.OptionalFunctions.emptyToFalse;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;

public class OptionalFunctionsTest {

    @Test
    public void blankAsNull_cases() {
        final Optional<String> optionalString = empty();
        assertThat(optionalString.map(blankAsNull)).isEmpty();
        assertThat(of("").map(blankAsNull)).isEmpty();
        assertThat(of(" ").map(blankAsNull)).isEmpty();
        assertThat(of("x").map(blankAsNull)).isNotEmpty();
        assertThat(of(" x").map(blankAsNull)).isNotEmpty();
        assertThat(of("x ").map(blankAsNull)).isNotEmpty();
        assertThat(of(" x ").map(blankAsNull)).isNotEmpty();
    }

    @Test
    public void blankToEmpty_cases() {
        final Optional<String> optionalString = empty();
        assertThat(blankToEmpty(optionalString)).isEmpty();
        assertThat(blankToEmpty(of(""))).isEmpty();
        assertThat(blankToEmpty(of(" "))).isEmpty();
        assertThat(blankToEmpty(of("x"))).isNotEmpty();
        assertThat(blankToEmpty(of(" x"))).isNotEmpty();
        assertThat(blankToEmpty(of("x "))).isNotEmpty();
        assertThat(blankToEmpty(of(" x "))).isNotEmpty();
    }

    @Test
    public void emptyToFalse_cases() {
        assertThat(emptyToFalse(empty())).isNotEmpty().get().isEqualTo(false);
        assertThat(emptyToFalse(of(true))).isNotEmpty().get().isEqualTo(true);
        assertThat(emptyToFalse(of(false))).isNotEmpty().get().isEqualTo(false);
    }
}
