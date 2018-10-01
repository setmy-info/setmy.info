package ee.pub.platform.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Smoke testing refers to physical tests made to closed systems of pipes to
 * test for leaks. By metaphorical extension, the term is also used for the
 * first test made after assembly or repairs to a system, to provide some
 * assurance that the system under test will not catastrophically fail. After a
 * smoke test proves that "the pipes will not leak, the keys seal properly, the
 * circuit will not burn, or the software will not crash outright,"[citation
 * needed] the system is ready for more stressful testing.
 *
 * @author Imre Tabur <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({SanityTests.class})
public class SmokeTests {
}
