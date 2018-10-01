package ee.pub.web;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.sourceforge.jwebunit.junit.WebTester;
import net.sourceforge.jwebunit.util.TestingEngineRegistry;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Imre Tabur <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class ExampleWebSolutionDefinitions {

    private static final String BASE_URL = "http://localhost:8080/tomcat-start-project";
    WebTester webTester;

    @Before
    public void beforeScenario() {
    }

    @Given("^new opened web connection to base url$")
    public void openingPage() throws Throwable {
        webTester = new WebTester();
        webTester.setTestingEngineKey(TestingEngineRegistry.TESTING_ENGINE_HTMLUNIT);
        webTester.setBaseUrl(BASE_URL);
    }

    @When("^(.*) page is opened$")
    public void pageOpenedWithoutErrors(String pageName) throws Throwable {
        webTester.beginAt(pageName);
    }

    @When("loged in with user (.*) and password (.*)$")
    public void userAndPassword(String userName, String password) throws Throwable {
        webTester.setTextField("j_username", userName);
        webTester.setTextField("j_password", password);
        webTester.submit();
    }

    @Then("^title should be (.*)$")
    public void titleShouldBe(String title) throws Throwable {
        webTester.assertTitleEquals(title);
    }

    @Then("^content (.*) on page should be (.*)$")
    public void contentPageShouldBe(String elementId, String text) throws Throwable {
        final String txt = webTester.getElementById(elementId).getTextContent();
        assertThat(txt, is(text));
    }
}
