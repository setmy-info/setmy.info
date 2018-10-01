package ee.pub.model;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.mockito.BDDMockito;
import static org.mockito.Mockito.mock;

/**
 * Person class example testing with Cucumber and Mockito in BDD style.
 *
 * @author Imre Tabur <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class PersonDefinitions {

    Person person;
    String firstName;
    String expectedFirstName;

    @Before
    public void beforeScenario() {
        firstName = null;
        person = null;
    }

    @Given("^newly created person object$")
    public void given() throws Throwable {
        person = mock(Person.class);
        expectedFirstName = "PeeterMeeter";
        BDDMockito.given(person.getFirstName()).willReturn(expectedFirstName);
    }

    @When("^getting first name$")
    public void when() throws Throwable {
        firstName = person.getFirstName();
    }

    @Then("^first name should be as expected$")
    public void then() throws Throwable {
        assertThat(firstName, is(expectedFirstName));
    }
}
