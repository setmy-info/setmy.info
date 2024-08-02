package info.setmy.models.storage;

import info.setmy.exceptions.ForbiddenException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static info.setmy.models.storage.DefaultStorageValidator.instance;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class StorageValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {
        "file.name",
        "/file.name",
        "/some/file.name",
        ".file.name",
        "/.file.name",
        "/some/.file.name"}
    )
    public void validateAgainstDirChanges(final String testable) {
        instance().validateAgainstDirChanges(testable);
    }

    @Test
    public void validateAgainstDirChangesEmpty() {
        instance().validateAgainstDirChanges("");
    }

    @Test
    public void validateAgainstDirChangesNull() {
        final var exceptionThrown = assertThrows(NullPointerException.class, () -> {
            instance().validateAgainstDirChanges(null);
        });
        assertThat(exceptionThrown.getMessage()).isEqualTo("Cannot invoke \"String.contains(java.lang.CharSequence)\" because \"fileName\" is null");
    }

    @ParameterizedTest
    @MethodSource("testableAndExceptionTextPairs")
    public void validateAgainstDirChanges(final String testable, final String expectedExceptionText) {
        final var exceptionThrown = assertThrows(ForbiddenException.class, () -> {
            instance().validateAgainstDirChanges(testable);
        });
        assertThat(exceptionThrown.getMessage()).isEqualTo(expectedExceptionText);
    }

    private static Stream<Arguments> testableAndExceptionTextPairs() {
        return Stream.of(
            Arguments.of("/.", "File or directory '/.' is not allowed"),
            Arguments.of("./", "File or directory './' is not allowed"),
            Arguments.of("./that", "File or directory './that' is not allowed"),
            Arguments.of("/./that", "File or directory '/./that' is not allowed"),
            Arguments.of("/root/or/./that", "File or directory '/root/or/./that' is not allowed"),
            Arguments.of("/root/or/.", "File or directory '/root/or/.' is not allowed"),
            Arguments.of("/root/or/./", "File or directory '/root/or/./' is not allowed"),
            Arguments.of("/..", "File or directory '/..' is not allowed"),
            Arguments.of("../", "File or directory '../' is not allowed"),
            Arguments.of("../that", "File or directory '../that' is not allowed"),
            Arguments.of("/../that", "File or directory '/../that' is not allowed"),
            Arguments.of("/root/or/../that", "File or directory '/root/or/../that' is not allowed"),
            Arguments.of("/root/or/../", "File or directory '/root/or/../' is not allowed"),
            Arguments.of("/root/or/..", "File or directory '/root/or/..' is not allowed"),
            Arguments.of("/root/../", "File or directory '/root/../' is not allowed"),
            Arguments.of("/root/..", "File or directory '/root/..' is not allowed"),
            Arguments.of("/home/or/../that", "File or directory '/home/or/../that' is not allowed"),
            Arguments.of("/home/or/../", "File or directory '/home/or/../' is not allowed"),
            Arguments.of("/home/or/..", "File or directory '/home/or/..' is not allowed"),
            Arguments.of("/home/../", "File or directory '/home/../' is not allowed"),
            Arguments.of("/home/..", "File or directory '/home/..' is not allowed")
        );
    }
}
