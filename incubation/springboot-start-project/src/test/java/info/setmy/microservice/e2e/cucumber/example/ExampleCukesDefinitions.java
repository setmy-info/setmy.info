package info.setmy.microservice.e2e.cucumber.example;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.ParameterType;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static info.setmy.microservice.Switches.E2E_TEST_CUCUMBER_FAILS;
import static org.assertj.core.api.Fail.fail;

/**
 * https://cucumber.io/docs/cucumber/api/#step-arguments
 * https://cucumber.io/docs/cucumber/api/
 * https://github.com/cucumber/cucumber-jvm/tree/master/java
 */
@Slf4j
public class ExampleCukesDefinitions {

    String firstName;
    String lastName;
    LocalDate date;
    BigDecimal money;
    int a;
    int b;
    int c;
    int sum;
    List<Map<String, String>> dataTable;
    List<String> animals;
    Book book;

    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
    public LocalDate iso8601Date(final String year, final String month, final String day) {
        return LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
    }

    @ParameterType("(.*?)")
    public LocalDate localDate(final String localDateString) {
        return LocalDate.parse(localDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @ParameterType(".*")
    public Book book(final String bookName) {
        return new Book(bookName);
    }

    @Before
    public void before() {
        log.info("Before scenario");
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.sum = 0;
        this.date = null;
        this.money = null;
        this.dataTable = null;
        this.animals = null;
        this.book = null;
    }

    @After
    public void after() {
        log.info("After scenario");
    }

    @BeforeStep
    public void doSomethingBeforeStep(final Scenario scenario) {
        log.info("Before step: {}", scenario.getName());
    }

    @AfterStep
    public void doSomethingAfterStep(final Scenario scenario) {
        log.info("After step: {}", scenario.getName());
    }

    @Given("first name is {string}")
    public void first_name_is(final String firstName) {
        log.info("And firstName={}", firstName);
        this.firstName = firstName;
    }

    @Given("last name is {string}")
    public void last_name_is(final String lastName) {
        log.info("And lastName={}", lastName);
        this.lastName = lastName;
    }

    @And("calculation date is {localDate}")
    public void calculation_date_is(final LocalDate localDate) {
        log.info("And date={}", localDate);
        this.date = localDate;
    }

    @Given("book name is {book}")
    public void book_name_is(final Book book) {
        log.info("And book={}", book);
        this.book = book;
    }

    @Given("money is {}")
    public void money_is(final BigDecimal money) {
        log.info("And money={}", money);
        this.money = money;
    }

    @Given("two numbers {int} and {int}")
    public void two_numbers_and(final Integer a, final Integer b) {
        log.info("Given: a={} and b={}", a, b);
        this.a = a;
        this.b = b;
    }

    @Given("the following users exist:")
    public void the_following_users_exist(final List<Map<String, String>> dataTable) {
        log.info("Given table: {}", dataTable);
        this.dataTable = dataTable;
    }

    @Given("the following animals:")
    public void the_following_animals(final List<String> animals) {
        log.info("Given anumals: {}", animals);
        this.animals = animals;
    }

    @And("third number is {int}")
    public void third_number_is(final Integer c) {
        log.info("And c={}", c);
        this.c = c;
    }

    @But("initial sum is {int}")
    public void initial_sum_is(final Integer sum) {
        log.info("But sum={}", sum);
        this.sum = sum;
    }

    @When("adding them")
    public void adding_them() {
        log.info("When adding them");
        this.sum = this.a + this.b + this.c;
    }

    @Then("sum should be {int}")
    public void them_summary_should_be(final Integer sum) {
        log.info("Then sum should be: {} and actual sum is: {}", sum, this.sum);
    }

    @Given("there are {int} cucumbers")
    public void there_are_cucumbers(final Integer num) {
        log.info("there_are_cucumbers: {}", num);
    }

    @When("I eat {int} cucumbers")
    public void i_eat_cucumbers(final Integer num) {
        log.info("there_are_cucumbers: {}", num);
    }

    @Then("I should have {int} cucumbers")
    public void i_should_have_cucumbers(final Integer num) {
        log.info("there_are_cucumbers: {}", num);
    }

    boolean expectToFail = false;


    @Given("expectation for fail is {string}")
    public void expectation_for_fail_is(final String expectToFailString) {
        expectToFail = expectToFailString.equalsIgnoreCase("true");
        log.info("Should fail: {}", expectToFail);
    }

    @When("doing nothing here")
    public void doing_nothing() {
    }

    @Then("this test should fail")
    public void it_should_fail() {
        if (expectToFail == E2E_TEST_CUCUMBER_FAILS) {
            fail("Script is failing");
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private class Book {
        private String name;
    }
}
