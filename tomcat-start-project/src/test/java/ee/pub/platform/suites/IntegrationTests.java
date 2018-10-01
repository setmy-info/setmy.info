package ee.pub.platform.suites;

import ee.pub.ServiceFakeIT;
import ee.pub.SpringConfIT;
import ee.pub.web.LoginIT;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Integration tests.
 *
 * mvn -Dtest=Integration test
 *
 * @author Imre Tabur <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({SpringConfIT.class, ServiceFakeIT.class, UnitTests.class, LoginIT.class})
public class IntegrationTests {
}
