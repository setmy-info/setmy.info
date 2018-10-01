package ee.pub.platform.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * All possible tests.
 *
 * mvn -Dtest=AllTests test
 *
 * @author Imre Tabur <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({SanityTests.class, SmokeTests.class, IntegrationTests.class})
public class AllTests {
}
