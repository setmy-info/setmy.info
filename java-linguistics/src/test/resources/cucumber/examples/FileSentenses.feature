Feature: sentences.txt

  Scenario: striped url
    Given text from row 1
    When parsing it
    Then it should be parsed into 3 tokens
    And the token should be "Tere"
    And the token should be "maailm"
    And the token should be "!"
    And there should be no more tokens
