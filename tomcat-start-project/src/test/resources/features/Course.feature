Feature: Course feature

    Scenario: a person with childrens
        Given newly created course object
        When getting students
        Then students list is expected list