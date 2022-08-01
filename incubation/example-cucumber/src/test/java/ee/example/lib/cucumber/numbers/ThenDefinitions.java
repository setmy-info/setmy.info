package ee.example.lib.cucumber.numbers;

import ee.example.lib.cucumber.numbers.models.Sum;
import io.cucumber.java.en.Then;
import org.assertj.core.api.Assertions;

public class ThenDefinitions {

    @Then("numbers sum should be {int}")
    public void numbers_sum_should_be(final int sum) {
        Assertions.assertThat(Sum.sumValue.getValue()).isEqualTo(sum);
    }
}
