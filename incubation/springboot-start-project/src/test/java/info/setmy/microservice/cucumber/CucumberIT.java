package info.setmy.microservice.cucumber;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.platform.engine.Cucumber;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;

//@Suite
@IncludeEngines("cucumber")
@Cucumber
@SelectClasspathResource("gherkin")
@CucumberOptions(
    features = {"src/test/gherkin"},
    plugin = {"pretty", "summary", "html:target/cucumber/examples/cucumber.html", "json:target/cucumber/examples/cucumber.json", "junit:target/cucumber/examples/cucumber.xml"}
)
public class CucumberIT {
}
