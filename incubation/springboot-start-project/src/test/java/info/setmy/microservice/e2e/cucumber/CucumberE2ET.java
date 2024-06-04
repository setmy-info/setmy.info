package info.setmy.microservice.e2e.cucumber;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;

/*
import io.cucumber.junit.platform.engine.Constants;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("info/setmy/microservice/cucumber")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "usage")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "html:target/cucumber-reports.html")
@SpringBootTest
@CucumberContextConfiguration
*/

//@Cucumber//separately UT and IT working. But not failing

@Suite
@IncludeEngines("cucumber")
@SelectPackages("info.setmy.microservice.e2e.cucumber")
@SelectClasspathResource("info/setmy/microservice/e2e/cucumber")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "info.setmy.microservice.e2e.cucumber")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "summary")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "usage")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "html:target/cucumber/examples/cucumber.html, json:target/cucumber/examples/cucumber.json, junit:target/cucumber/examples/cucumber.xml, message:target/cucumber/examples/results.ndjson")
public class CucumberE2ET {
}
