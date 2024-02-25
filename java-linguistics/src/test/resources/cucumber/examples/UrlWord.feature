Feature: URL-s as word

  Scenario: xx
    Given text is "https://example.com/url/atr?abs=123%2C"
    When parsing it
    Then it should be parsed into 1 tokens
    And should have token "https://example.com/url/atr?abs=123%2C"
    And no more tokens

  Scenario: xx
    Given text is " https://example.com/url/atr?abs=123%2C  "
    When parsing it
    Then it should be parsed into 1 tokens
    And should have token "https://example.com/url/atr?abs=123%2C"
    And no more tokens
