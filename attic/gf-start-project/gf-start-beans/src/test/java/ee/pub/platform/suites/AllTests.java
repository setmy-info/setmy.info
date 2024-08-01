package ee.pub.platform.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * All possible tests.
 *
 * mvn -Dtest=AllTests test
 *
 * @author Imre Tabur <imre.tabur@mail.ee>
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({SanityTests.class, SmokeTests.class, Integration.class})
public class AllTests {
}
