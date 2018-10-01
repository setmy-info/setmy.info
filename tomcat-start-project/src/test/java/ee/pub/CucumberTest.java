package ee.pub;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 *
 * @author Imre Tabur <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
            "pretty",
            "html:target/cucumber",
            "json:target/cucumber/cucumber.json"
        }, features = {
            "classpath:features/Course.feature"
        })
public class CucumberTest {
}
