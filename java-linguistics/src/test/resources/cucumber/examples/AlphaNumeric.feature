Feature: parsing only alphanumeric and white character sentences, without any other characters

  Scenario: single alphabetic words
    Given text is "Hello"
    When parsing it
    Then it should be parsed into 1 tokens
    And should have token "Hello"
    And no more tokens

  Scenario: single alphabetic words both sides un striped
    Given text is "  Hello  "
    When parsing it
    Then it should be parsed into 1 tokens
    And should have token "Hello"
    And no more tokens

  Scenario: single alphabetic words right side un striped
    Given text is "Hello  "
    When parsing it
    Then it should be parsed into 1 tokens
    And should have token "Hello"
    And no more tokens

  Scenario: single alphabetic words left side un striped
    Given text is "  Hello"
    When parsing it
    Then it should be parsed into 1 tokens
    And should have token "Hello"
    And no more tokens

  Scenario: double word sentence
    Given text is "Hello World"
    When parsing it
    Then it should be parsed into 2 tokens
    And should have token "Hello"
    And should have token "World"
    And no more tokens

  Scenario: double word sentence un striped
    Given text is "  Hello   World "
    When parsing it
    Then it should be parsed into 2 tokens
    And should have token "Hello"
    And should have token "World"
    And no more tokens

  Scenario: only alphabet words
    Given text is "  aa   bb   cc  "
    When parsing it
    Then it should be parsed into 3 tokens
    And should have token "aa"
    And should have token "bb"
    And should have token "cc"
    And no more tokens

  Scenario: only numeric words
    Given text is "  11 22 33 44  "
    When parsing it
    Then it should be parsed into 4 tokens
    And should have token "11"
    And should have token "22"
    And should have token "33"
    And should have token "44"
    And no more tokens

  Scenario: numeric and alphabetic words
    Given text is "  aa 22 bb 44  cc  "
    When parsing it
    Then it should be parsed into 5 tokens
    And should have token "aa"
    And should have token "22"
    And should have token "bb"
    And should have token "44"
    And should have token "cc"
    And no more tokens
