package ee.pub.platform.suites;

import ee.pub.model.CourseTest;
import ee.pub.model.PersonTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Integration tests.
 *
 * mvn -Dtest=UnitTests test
 *
 * @author Imre Tabur <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({CourseTest.class, PersonTest.class})
public class UnitTests {
}
