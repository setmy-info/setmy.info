package info.setmy.models

//http://blog.andresteingress.com/2014/01/28/functional-testing-with-cucumber
/*
import static cucumber.api.groovy.EN.*
import static cucumber.api.groovy.Hooks.*

Before() {
println "Before scenario"
this.numberA = 0
this.numberB = 0
this.result = 0
}

After() {
println "After scenario"
}
// FOR STRINGS: "([^"]*)"
Given(~'^two numbers (\\d+) and (\\d+)$') { int a, int b ->
println "givern numbers ${a} ${b}"
this.numberA = a
this.numberB = b
}

When(~'^adding them$') { ->
this.result = numberA + numberB
println "adding ${numberA} and ${numberB} = ${result}"
}

Then(~'^them summary should be (\\d+)$') { int c ->
println "comparing numbers summary ${result} with expected ${c}"
assert result == c
}
 */
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import static org.mockito.Mockito.when
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.verify
import cucumber.api.java.After
import cucumber.api.java.Before

public class ExampleCukesDefinitions {

    int numberA
    int numberB
    int result

    @Before
    public void before() {
        println "Before scenario"
        this.numberA = 0
        this.numberB = 0
        this.result = 0
    }

    @After
    public void after() {
        println "After scenario"
    }
    
    @Given("^two numbers (\\d+) and (\\d+)")//String: "^\"([^\"]*)\""
    public void "two numbers a and b"(int a, int b) {
        println "givern numbers ${a} ${b}"
        this.numberA = a
        this.numberB = b
    }

    @When("^adding them")
    public void "adding them"() {
        result = numberA +  numberB
        println "adding ${numberA} and ${numberB} = ${result}"
    }

    @Then("^them summary should be (\\d+)")
    public void "them summary should be b"(int c) {
        println "comparing numbers summary ${result} with expected ${c}"
        assert result == c
    }
}
