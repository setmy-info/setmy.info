package ee.pub.web;

import com.jayway.restassured.RestAssured;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.Matchers.containsString;

/**
 *
 * @author Imre Tabur <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class ExampleRestSolutionDefinitions extends RestBase {

    @Before
    public void beforeScenario() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.port = PORT;
    }

    @Given("^REST resource (.*)$")
    public void restResource(final String reource) throws Throwable {
        given = getGiven(reource);
    }

    @When("^getting person by first name (.*) and last name (.*)$")
    public void gettingPersonByFirstaneLastName(final String firstName, final String lastName) throws Throwable {
        then = when.get("/example/person/first/" + firstName + "/last/" + lastName).then();
    }

    @Then("^result (.*) should contain (.*)$")
    public void then(final String value, final String content) throws Throwable {
        then.assertThat().body(value, containsString(content));
    }

    @Then("^result code should end with code (\\d+)$")
    public void thenResultCodeShouldEndWithCode(final int code) throws Throwable {
        then.assertThat().statusCode(code);
    }
}
