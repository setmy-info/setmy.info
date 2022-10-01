package ee.example.lib.cucumber.numbers;

import ee.example.lib.MathService;
import ee.example.lib.cucumber.numbers.models.Sum;
import io.cucumber.java.Before;
import io.cucumber.java.en.When;

import static ee.example.lib.cucumber.numbers.models.NumberA.numberAValue;
import static ee.example.lib.cucumber.numbers.models.NumberB.numberBValue;

public class WhenDefinitions {

    @Before
    public void before() {
        System.out.println("Before numbers When");
        Sum.sumValue = null;
    }

    @When("adding number a and b")
    public void adding_number_a_and_b() {
        final MathService mathService = new MathService();
        final int a = numberAValue.getValue();
        final int b = numberBValue.getValue();
        Sum.sumValue = new Sum(mathService.add(a, b));
    }
}
