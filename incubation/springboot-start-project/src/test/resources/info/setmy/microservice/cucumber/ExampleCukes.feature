# language: en
# https://cucumber.io/docs/gherkin/reference/
@calculation @math @smoke @fast @Failsafe
Feature: example cukes

    Background:
        Given spring boot environment is started
        Given first name is "Imre"
        And last name is "Tabur"
        And book name is "Best Seller"
        And calculation date is 2020-12-31
        And money is 123.45
        And the following users exist:
          | name   | email              | twitter         |
          | Aslak  | aslak@cucumber.io  | @aslak_hellesoy |
          | Julien | julien@cucumber.io | @jbpros         |
          | Matt   | matt@cucumber.io   | @mattwynne      |
        And the following animals:
          | cow   |
          | horse |
          | sheep |

    @important @example
    Scenario: first with numbers
        Given two numbers 1 and 2
        And third number is 4
        But initial sum is 0
        When adding them
        Then sum should be 7

    @wip
    Scenario: second with numbers
        Given two numbers 3 and 2
        And third number is 1
        When adding them
        Then sum should be 6

    Scenario Outline: eating
        Given there are <start> cucumbers
        When I eat <eat> cucumbers
        Then I should have <left> cucumbers

        Examples:
            | start | eat | left |
            |    12 |   5 |    7 |
            |    20 |   5 |   15 |
