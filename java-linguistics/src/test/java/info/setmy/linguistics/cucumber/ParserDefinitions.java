package info.setmy.linguistics.cucumber;

import info.setmy.linguistics.Parser;
import info.setmy.linguistics.models.token.Token;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static info.setmy.linguistics.ResourcesUtil.getTestFileName;
import static info.setmy.models.FileRows.newFileRows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserDefinitions {

    private Parser parser;
    private String string;
    private List<Token> result;
    private int tokenCounter;

    private List<String> sentences;

    @Before
    public void before() {
        parser = new Parser();
        string = "";
        result = new ArrayList<>();
        tokenCounter = 0;
    }

    @Given("file {string} is read in as lines in list")
    public void readFileIn(final String fileName) {
        sentences = newFileRows(getTestFileName(ParserDefinitions.class, fileName)).get().getRows();
    }

    @Given("the text is {string}")
    public void textIs(final String string) {
        this.string = string;
    }

    @Given("text from row {int}")
    public void textFromRow(final Integer rowNumber) {
        string = sentences.get(rowNumber - 1);
    }

    @When("parsing it")
    public void parsingIt() {
        result = parser.parse(string);
    }

    @Then("it should be parsed into {int} tokens")
    public void tokensNumber(final Integer tokensNumber) {
        assertThat(result).hasSize(tokensNumber);
    }

    @And("the token should be {string}")
    public void andShouldHave(final String string) {
        assertThat(result.get(tokenCounter).toString()).isEqualTo(string);
        tokenCounter++;
    }

    @And("the token should be {string}, that is {isInType}")
    public void andShouldHaveInType(final String string, final Function<Token, Boolean> isInType) {
        final Token token = result.get(tokenCounter);
        assertThat(token.toString()).isEqualTo(string);
        assertTrue(isInType.apply(token));
        tokenCounter++;
    }

    @And("there should be no more tokens")
    public void thatIsAll() {
        assertThat(result.size()).isEqualTo(tokenCounter);
    }
}
