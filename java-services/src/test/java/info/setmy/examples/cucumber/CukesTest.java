package info.setmy.examples.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "summary", "html:target/cucumber/examples/cucumber.html", "json:target/cucumber/examples/cucumber.json"},
        strict = true,
        features = {"src/test/cucumber/examples"}
)
public class CukesTest {
}
