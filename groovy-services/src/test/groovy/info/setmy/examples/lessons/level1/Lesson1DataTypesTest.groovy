package info.setmy.examples.lessons.level1

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
@TestMethodOrder(OrderAnnotation)
class Lesson1DataTypesTest {

    private static final Logger LOG = LoggerFactory.getLogger(Lesson1DataTypesTest)

    @BeforeAll
    public static void beforeAll() {
        LOG.info("beforeAll")
    }

    @BeforeEach
    public void beforeEach(final TestInfo testInfo) throws InterruptedException {
        LOG.info("beforeEach: {}", testInfo.getDisplayName());
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
    void helloWorld() {
        println "Hello World!"
    }

    @Test
    void map() {
        def map = [:]
        def firstname = "Homer"
        def colors = [red: '#FF0000', green: '#00FF00', blue: '#0000FF']
        map."an identifier with a space and double quotes" = "ALLOWED"
        map.'with-dash-signs-and-single-quotes' = "ALLOWED"
        map."Simpson-${firstname}" = "Homer Simpson"

        assert map."an identifier with a space and double quotes" == "ALLOWED"
        assert map.'with-dash-signs-and-single-quotes' == "ALLOWED"
        assert map.'Simpson-Homer' == "Homer Simpson"
        assert colors['red'] == '#FF0000'
        assert colors.green  == '#00FF00'

        colors['pink'] = '#FF00FF'
        colors.yellow  = '#FFFF00'

        assert colors.pink == '#FF00FF'
        assert colors['yellow'] == '#FFFF00'
        assert colors instanceof java.util.LinkedHashMap
    }
}

