package info.setmy.microservice.cucumber.ut;

import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("cucumber")
public class CukesTest {
}
