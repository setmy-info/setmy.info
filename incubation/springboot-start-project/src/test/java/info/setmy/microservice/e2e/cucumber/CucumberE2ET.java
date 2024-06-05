package info.setmy.microservice.e2e.cucumber;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectPackages("info.setmy.microservice.e2e.cucumber")//*.feature  location
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "info.setmy.microservice.e2e.cucumber")//*Definitions location
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "summary")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "usage")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "html:target/cucumber/examples/cucumber.html, json:target/cucumber/examples/cucumber.json, junit:target/cucumber/examples/cucumber.xml, message:target/cucumber/examples/results.ndjson")
public class CucumberE2ET {
}
