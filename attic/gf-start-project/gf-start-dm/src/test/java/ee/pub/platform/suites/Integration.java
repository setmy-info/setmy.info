package ee.pub.platform.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Integration tests.
 *
 * mvn -Dtest=Integration test
 *
 * @author Imre Tabur <imre.tabur@eesti.ee>
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({UnitTests.class, ExampleTest.class})
public class Integration {
}
