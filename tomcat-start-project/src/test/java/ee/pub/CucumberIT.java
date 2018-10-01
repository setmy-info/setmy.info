package ee.pub;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * CucumberIT integration test class.
 *
 * mvn clean install
 *
 * @author Imre Tabur <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
            "pretty",
            "html:target/cucumber.it",
            "json:target/cucumber.it/cucumber.json"
        }, features = {
            "classpath:features/Person.feature",
            "classpath:features/ExampleWebSolution.feature",
            "classpath:features/ExampleRestSolution.feature"
        })
public class CucumberIT {
}
