# language: en
Feature: repository cloning and checking out

  Scenario: repository cloning
    Given git repository "git@github.com:setmy-info/stealer-test-a.git" with short name "stealer-test-a"
    And git repository "git@github.com:setmy-info/stealer-test-b.git" with short name "stealer-test-b"
    When initializing
    And stealing
    Then "stealer-test-a" folder should exist
    And "stealer-test-b" folder should exist
