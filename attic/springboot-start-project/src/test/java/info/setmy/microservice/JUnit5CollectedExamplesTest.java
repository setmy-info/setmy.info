package info.setmy.microservice;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
@Tag("fast")
@DisplayName("JUnit 5 sceleton")
@TestMethodOrder(OrderAnnotation.class)
public class JUnit5CollectedExamplesTest {

    private static final Logger log = LogManager.getLogger(JUnit5CollectedExamplesTest.class);

    @BeforeAll
    public static void initAll() {
        log.info("beforeEach");
    }

    @BeforeEach
    public void beforeEach(final TestInfo testInfo) throws InterruptedException {
        log.info("beforeEach: {}", testInfo.getDisplayName());
    }

    @AfterEach
    public void afterEach() {
        log.info("afterEach");
    }

    @AfterEach
    public void afterEach(final TestInfo testInfo) {
        log.info("afterEach: {}", testInfo.getDisplayName());
    }

    @AfterAll
    public static void afterAll() {
        log.info("afterAll");
    }

    @Test
    public void skippedTest() {
        log.info("skippedTest");
    }
}
