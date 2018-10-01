package ee.pub.platform.suites;

import ee.pub.ServiceFakeIT;
import ee.pub.SpringConfIT;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * A sanity test or sanity check is a basic test to quickly evaluate whether a
 * claim or the result of a calculation can possibly be true. It is a simple
 * check to see if the produced material is rational (that the material's
 * creator was thinking rationally, applying sanity).
 *
 * @author Imre Tabur <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({SpringConfIT.class, ServiceFakeIT.class, UnitTests.class})
public class SanityTests {
}
