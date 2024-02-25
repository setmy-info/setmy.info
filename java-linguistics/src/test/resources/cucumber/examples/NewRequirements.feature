Feature: New requirements
# non alphanumeric is separator. Whitespaces ar "continuous text" separator.
#
  Scenario: striped url
    Given the text is ""
    When parsing it
    #Then it should be parsed into 1 tokens
    #And the token should be ""
   # And there should be no more tokens
