package info.setmy.models

import org.junit.platform.suite.api.IncludeEngines
import org.junit.platform.suite.api.SelectClasspathResource
import org.junit.platform.suite.api.Suite

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("cucumber")
//@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
public class CukesTest {
}
