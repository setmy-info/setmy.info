Feature: parsing only alphanumeric and white character sentences, without any other characters

	Scenario: single alphabetic words
		Given the text is "Hello"
		When parsing it
		Then it should be parsed into 1 tokens
		And the token should be "Hello"
		And there should be no more tokens

	Scenario: single alphabetic words both sides un striped
		Given the text is "  Hello  "
		When parsing it
		Then it should be parsed into 3 tokens
		And the token should be " "
		And the token should be "Hello"
		And the token should be " "
		And there should be no more tokens

	Scenario: single alphabetic words right side un striped
		Given the text is "Hello  "
		When parsing it
		Then it should be parsed into 2 tokens
		And the token should be "Hello"
		And the token should be " "
		And there should be no more tokens

	Scenario: single alphabetic words left side un striped
		Given the text is "  Hello"
		When parsing it
		Then it should be parsed into 2 tokens
		And the token should be " "
		And the token should be "Hello"
		And there should be no more tokens

	Scenario: double word sentence
		Given the text is "Hello  World"
		When parsing it
		Then it should be parsed into 3 tokens
		And the token should be "Hello"
		And the token should be " "
		And the token should be "World"
		And there should be no more tokens

	Scenario: double word sentence un striped
		Given the text is " 	 Hello 	 World 	 "
		When parsing it
		Then it should be parsed into 5 tokens
		And the token should be " "
		And the token should be "Hello"
		And the token should be " "
		And the token should be "World"
		And the token should be " "
		And there should be no more tokens

	Scenario: only alphabet words
		Given the text is "   aa   bb   cc   "
		When parsing it
		Then it should be parsed into 7 tokens
		And the token should be " "
		And the token should be "aa"
		And the token should be " "
		And the token should be "bb"
		And the token should be " "
		And the token should be "cc"
		And the token should be " "
		And there should be no more tokens

	Scenario: only numeric words
		Given the text is "  11 22 33 44  "
		When parsing it
		Then it should be parsed into 9 tokens
		And the token should be " "
		And the token should be "11"
		And the token should be " "
		And the token should be "22"
		And the token should be " "
		And the token should be "33"
		And the token should be " "
		And the token should be "44"
		And the token should be " "
		And there should be no more tokens

	Scenario: numeric and alphabetic words
		Given the text is "  aa 22 bb 44  cc  "
		When parsing it
		Then it should be parsed into 11 tokens
		And the token should be " "
		And the token should be "aa"
		And the token should be " "
		And the token should be "22"
		And the token should be " "
		And the token should be "bb"
		And the token should be " "
		And the token should be "44"
		And the token should be " "
		And the token should be "cc"
		And the token should be " "
		And there should be no more tokens
