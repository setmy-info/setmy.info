package info.setmy.models

import org.junit.Test
import org.junit.runner.RunWith
import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber

@RunWith(Cucumber)
@CucumberOptions(
        format = [
                "pretty", "html:target/cucumber",
                "json:target/cucumber/cucumber.json"],
        strict = true,
        features = ["src/test/cucumber"]
)
public class CukesTest {
}
