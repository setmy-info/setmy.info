package ee.example.lib.cucumber.numbers;

import ee.example.lib.cucumber.numbers.models.NumberA;
import ee.example.lib.cucumber.numbers.models.NumberB;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;

public class GivenDefinitions {

    @Before
    public void before() {
        System.out.println("Before numbers Givern");
        NumberA.numberAValue = null;
        NumberB.numberBValue = null;
    }

    @Given("number a is {int}")
    public void number_a_is(final int a) {
        NumberA.numberAValue = new NumberA(a);
    }

    @Given("number b is {int}")
    public void number_b_is(final int b) {
        NumberB.numberBValue = new NumberB(b);
    }
}
