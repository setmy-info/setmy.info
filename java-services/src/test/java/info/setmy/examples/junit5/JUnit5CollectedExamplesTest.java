package info.setmy.examples.junit5;

import info.setmy.examples.junit5.RandomParametersExtension.Random;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.After;
import org.junit.Before;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumingThat;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import static org.junit.jupiter.api.condition.OS.*;
import static org.junit.jupiter.api.condition.JRE.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Tag("fast")
@DisplayName("JUnit 5 collection examples")
@ExtendWith(RandomParametersExtension.class) //MockitoExtension, SpringExtension
@TestMethodOrder(OrderAnnotation.class)
public class JUnit5CollectedExamplesTest {

    private static final Logger LOG = LoggerFactory.getLogger(JUnit5CollectedExamplesTest.class);

    @BeforeAll
    public static void initAll() {
        LOG.info("beforeEach");
    }

    @BeforeEach
    @Timeout(2)
    public void beforeEach(final TestInfo testInfo) throws InterruptedException {
        LOG.info("beforeEach: {}", testInfo.getDisplayName());
        //Thread.sleep(seconds(3));
    }

    @Before
    public void before() {
        LOG.info("before");
    }

    @After
    public void after() {
        LOG.info("after");
    }

    @AfterEach
    public void afterEach(final TestInfo testInfo) {
        LOG.info("afterEach: {}", testInfo.getDisplayName());
    }

    @AfterAll
    public static void afterAll() {
        LOG.info("afterAll");
    }

    @Order(1)
    @Test
    @Disabled("for demonstration purposes")
    public void skippedTest() {
        LOG.info("skippedTest");
        fail("test should have not been executed");
    }

    @Order(2)
    @Tag("evenFaster")
    @FastTest// Same as: @Tag("fast") @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    public void fastTest() throws InterruptedException {
        LOG.info("fastTest");
        assumeTrue(true);
        assertThat(true).isEqualTo(true);
        assumingThat("value".equals("another value"), () -> {
            assertEquals(2, 3);
        });
        assumingThat("value".equals("value"), () -> {
            assertEquals(2, 2);
        });
        //Thread.sleep(200L);
    }

    @Order(3)
    @DisplayName("A negative value for year is not supported by the leap year computation.")
    @ParameterizedTest(name = "For example, year {0} is not supported.")
    @NullSource
    @ValueSource(ints = {-1, -4})
    public void ifItIsNegative(Integer year) {
        if (year == null) {
            return;
        }
        if (year < 0) {
            //fail("Less than 0");
        }
    }

    @Order(3)
    @DisplayName("Name !!!")
    @ParameterizedTest(name = "Text {0} under test")
    @NullSource
    @EmptySource
    @ValueSource(strings = {" ", "   ", "\t", "\n", "Hello", "World", ""})
    public void texts(String text) {
        LOG.info("testing texts: {}", text);
    }

    @Order(4)
    @RepeatedTest(3)
    @DisplayName("display name: test 1 ")
    public void test1() {
        LOG.info("### Testing 1 ###");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World"})
    @DisplayName("display name: test 2 ")
    public void test2(final String word) {
        LOG.info("### Testing 2 {} ###", word);
    }

    @Order(5)
    @Test
    @DisplayName(", display name: test 3 ")
    public void test3() {
        LOG.info("### Testing 3 ###");
    }

    @Order(6)
    @org.junit.jupiter.api.Test
    @Disabled
    @DisplayName(" example test 4 ")
    public void test4(final TestInfo testInfo) {
        LOG.info("### Testing 4 ###");
        fail("Just failing {}" + testInfo.getDisplayName());
    }

    @Order(7)
    @Test
    @EnabledOnOs({LINUX, MAC, WINDOWS})
    public void osActive(final TestInfo testInfo, TestReporter testReporter) {
        LOG.info("Executed on some OS-s");
        testReporter.publishEntry("a status message");
    }

    @Order(8)
    @Test
    @DisabledOnOs(WINDOWS)
    void notOnWindows() {
        LOG.info("Not executed on Windows");
    }

    @Order(9)
    @Test
    @EnabledOnJre({JAVA_8, JAVA_9, JAVA_10, JAVA_11, JAVA_12, JAVA_13})
    void onlyOnSomeJREs() {
        LOG.info("executed on correct JRE versions!");
    }

    @Order(10)
    @Test
    @EnabledForJreRange(max = JAVA_12)
    void onJRERange() {
        LOG.info("Up to {} JRE not allowed", JAVA_12);
        fail("Not allowed JRE!");
    }

    @Order(11)
    @Test
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
    void onlyOn64BitArchitectures() {
        LOG.info("Allowed architecture!");
    }

    @Order(12)
    @Test
    @EnabledIfEnvironmentVariable(named = "TEST_ENVIRONMENT", matches = "CI")
    void onlyOnCIServer() {
        LOG.info("executed when environment variable is set with correct value");
    }

    @Order(13)
    @Test
    @DisabledIfEnvironmentVariable(named = "TEST_ENVIRONMENT", matches = "CI")
    void notOnCIServer() {
        LOG.info("execute is disabled when environment variable is set with correct value");
    }

    @Order(14)
    @Test
    @DisplayName("repeated test")
    @RepeatedTest(value = 5, name = "Current {displayName}: {currentRepetition} to {totalRepetitions}")
    void repeatedTestInGerman() {
        LOG.info("repeating");
    }

    @Order(15)
    @ParameterizedTest
    @NullSource
    @EnumSource(value = NumbersEnum.class, mode = EnumSource.Mode.EXCLUDE, names = {"ONE"})
    void testWithEnumSource(NumbersEnum number) {
        LOG.info("Enum: {}", number);
    }

    @Order(15)
    @ParameterizedTest
    @ValueSource(strings = "ONE")
    void testWithImplicitArgumentConversion(NumbersEnum number) {
        assertNotNull(number.name());
        LOG.info("Enum: {}", number);
    }

    @Order(16)
    @ParameterizedTest
    @ArgumentsSource(ForTestingrgumentsProvider.class)
    void testWithArgumentsSource(String argument) {
        LOG.info("From provider: {}", argument);
        assertNotNull(argument);
    }

    @ParameterizedTest
    @ValueSource(strings = "JUnit book")
    void bookTest(Book book) {
        assertEquals("JUnit book", book.getTitle());
    }

    @Test
    void injectsInteger(@Random int i, @Random int j) {
        assertNotEquals(i, j);
    }

    @Test
    void injectsDouble(@Random double d) {
        assertEquals(0.0, d, 1.0);
    }

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class A_year_is_not_supported {

        @Test
        void if_it_is_zero() {
        }

        @DisplayName("A negative value for year is not supported by the leap year computation.")
        @ParameterizedTest(name = "For example, year {0} is not supported.")
        @ValueSource(ints = {-1, -4})
        void if_it_is_negative(int year) {
        }

    }

    @Nested
    @DisplayNameGeneration(IndicativeSentences.class)
    class A_year_is_a_leap_year {

        @Test
        void if_it_is_divisible_by_4_but_not_by_100() {
            LOG.info("if_it_is_divisible_by_4_but_not_by_100");
        }

        @ParameterizedTest(name = "Year {0} is a leap year.")
        @ValueSource(ints = {2016, 2020, 2048})
        void if_it_is_one_of_the_following_years(int year) {
            LOG.info("if_it_is_one_of_the_following_years");
        }

    }

    static class IndicativeSentences extends DisplayNameGenerator.ReplaceUnderscores {

        @Override
        public String generateDisplayNameForClass(Class<?> testClass) {
            return super.generateDisplayNameForClass(testClass);
        }

        @Override
        public String generateDisplayNameForNestedClass(Class<?> nestedClass) {
            return super.generateDisplayNameForNestedClass(nestedClass) + "...";
        }

        @Override
        public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
            LOG.info("generateDisplayNameForMethod");
            String name = testClass.getSimpleName() + ' ' + testMethod.getName();
            return name.replace('_', ' ') + '.';
        }
    }

    private long seconds(int i) {
        return 1000 * 1;
    }
}
