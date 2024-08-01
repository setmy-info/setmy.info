package info.setmy.models

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Exception to roll back transactions.
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
@TestMethodOrder(OrderAnnotation)
class ExampleSecondTest {

    private static final Logger LOG = LoggerFactory.getLogger(ExampleSecondTest)

    ExampleSecond example

    @BeforeAll
    public static void beforeAll() {
        LOG.info("beforeAll")
    }

    @BeforeEach
    @Timeout(2L)
    public void beforeEach(final TestInfo testInfo) throws InterruptedException {
        LOG.info("beforeEach: {}", testInfo.getDisplayName());
        //Thread.sleep(seconds(3));
        example = new ExampleSecond()
        example.firstName = "Imre"
    }

    @AfterEach
    public void afterEach(final TestInfo testInfo) {
        LOG.info("afterEach: {}", testInfo.getDisplayName());
    }

    @AfterAll
    public static void afterAll() {
        LOG.info("afterAll");
    }

    @Test
    void test() {
        assert example.firstName == "Imre"
    }
}
