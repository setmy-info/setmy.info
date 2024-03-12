package info.setmy.microservice.cucumber;

import io.cucumber.java.*;
import io.cucumber.java.en.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * https://cucumber.io/docs/cucumber/api/#step-arguments
 * https://cucumber.io/docs/cucumber/api/
 * https://github.com/cucumber/cucumber-jvm/tree/master/java
 */
public class ExampleCukesDefinitions {

    private static final Logger log = LogManager.getLogger(ExampleCukesDefinitions.class);

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
    public LocalDate iso8601Date(String year, String month, String day) {
        return LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
    }

    @ParameterType("(.*?)")
    public LocalDate localDate(String localDateString) {
        return LocalDate.parse(localDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @ParameterType(".*")
    public Book book(String bookName) {
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
    public void doSomethingBeforeStep(Scenario scenario) {
        log.info("Before step: {}", scenario.getName());
    }

    @AfterStep
    public void doSomethingAfterStep(Scenario scenario) {
        log.info("After step: {}", scenario.getName());
    }

    @Given("first name is {string}")
    public void first_name_is(String firstName) {
        log.info("And firstName={}", firstName);
        this.firstName = firstName;
    }

    @Given("last name is {string}")
    public void last_name_is(String lastName) {
        log.info("And lastName={}", lastName);
        this.lastName = lastName;
    }

    @And("calculation date is {localDate}")
    public void calculation_date_is(LocalDate localDate) {
        log.info("And date={}", localDate);
        this.date = localDate;
    }

    @Given("book name is {book}")
    public void book_name_is(Book book) {
        log.info("And book={}", book);
        this.book = book;
    }

    @Given("money is {}")
    public void money_is(BigDecimal money) {
        log.info("And money={}", money);
        this.money = money;
    }

    @Given("two numbers {int} and {int}")
    public void two_numbers_and(Integer a, Integer b) {
        log.info("Given: a={} and b={}", a, b);
        this.a = a;
        this.b = b;
    }

    @Given("the following users exist:")
    public void the_following_users_exist(List<Map<String, String>> dataTable) {
        log.info("Given table: {}", dataTable);
        this.dataTable = dataTable;
    }

    @Given("the following animals:")
    public void the_following_animals(List<String> animals) {
        log.info("Given anumals: {}", animals);
        this.animals = animals;
    }

    @And("third number is {int}")
    public void third_number_is(Integer c) {
        log.info("And c={}", c);
        this.c = c;
    }

    @But("initial sum is {int}")
    public void initial_sum_is(Integer sum) {
        log.info("But sum={}", sum);
        this.sum = sum;
    }

    @When("adding them")
    public void adding_them() {
        log.info("When adding them");
        this.sum = this.a + this.b + this.c;
    }

    @Then("sum should be {int}")
    public void them_summary_should_be(Integer sum) {
        log.info("Then sum should be: {} and actual sum is: {}", sum, this.sum);
    }

    @Given("there are {int} cucumbers")
    public void there_are_cucumbers(Integer num) {
        log.info("there_are_cucumbers: {}", num);
    }

    @When("I eat {int} cucumbers")
    public void i_eat_cucumbers(Integer num) {
        log.info("there_are_cucumbers: {}", num);
    }

    @Then("I should have {int} cucumbers")
    public void i_should_have_cucumbers(Integer num) {
        log.info("there_are_cucumbers: {}", num);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private class Book {
        private String name;
    }
}
