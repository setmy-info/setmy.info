package info.setmy.stealer;

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
        features = {"src/test/cucumber"}
)
public class CukesIT {
}
