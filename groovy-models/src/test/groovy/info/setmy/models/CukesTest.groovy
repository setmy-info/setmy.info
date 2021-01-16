package info.setmy.models

import org.junit.Test
import org.junit.runner.RunWith
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@RunWith(Cucumber)
@CucumberOptions(
    plugin = [
                "pretty",
                "html:target/cucumber/cucumber.html",
                "json:target/cucumber/cucumber.json"
    ],
    strict = true,
    features = ["src/test/cucumber"]
)
public class CukesTest {
}
