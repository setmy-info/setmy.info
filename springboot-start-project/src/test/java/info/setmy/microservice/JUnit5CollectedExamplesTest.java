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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Tag("fast")
@DisplayName("JUnit 5 sceleton")
@TestMethodOrder(OrderAnnotation.class)
public class JUnit5CollectedExamplesTest {

    private static final Logger LOG = LoggerFactory.getLogger(JUnit5CollectedExamplesTest.class);

    @BeforeAll
    public static void initAll() {
        LOG.info("beforeEach");
    }

    @BeforeEach
    public void beforeEach(final TestInfo testInfo) throws InterruptedException {
        LOG.info("beforeEach: {}", testInfo.getDisplayName());
    }

    @AfterEach
    public void afterEach() {
        LOG.info("afterEach");
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
    public void skippedTest() {
        LOG.info("skippedTest");
    }
}
