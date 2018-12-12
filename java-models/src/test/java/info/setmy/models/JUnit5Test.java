package info.setmy.models;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class JUnit5Test {

    @BeforeEach
    public void beforeEach(final TestInfo testInfo) {
        System.out.println("* Before each: " + testInfo.getDisplayName());
    }

    @AfterEach
    public void afterEach(final TestInfo testInfo) {
        System.out.println("* After each: " + testInfo.getDisplayName());
    }

    @RepeatedTest(3)
    @DisplayName(", display name: test 1 ")
    public void test1() {
        System.out.println("### Testing 1 ###");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World"})
    @DisplayName(", display name: test 2 ")
    public void test2(final String word) {
        System.out.println("### Testing 2 " + word + " ###");
    }

    @Test
    @DisplayName(", display name: test 3 ")
    public void test3() {
        System.out.println("### Testing 3 ###");
    }

    @Test
    @DisplayName(" example test 4 ")
    public void test4(final TestInfo testInfo) {
        System.out.println("### Testing 4 ###");
        fail("Just failing " + testInfo.getDisplayName());
    }
}
