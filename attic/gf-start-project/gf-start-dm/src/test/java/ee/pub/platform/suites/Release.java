package ee.pub.platform.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Releasing tests (for CI).
 *
 * mvn -Dtest=Release test
 *
 * @author Imre Tabur <imre.tabur@mail.ee>
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({AllTests.class})
public class Release {
}
