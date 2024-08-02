Feature: parsing only alphanumeric and white character sentences, without any other characters

	Scenario: string without length
		Given the text is ""
		When parsing it
		Then it should be parsed into 0 tokens
		And there should be no more tokens

	Scenario: empty string
		Given the text is " "
		When parsing it
		Then it should be parsed into 1 tokens
		And the token should be " ", that is WhiteCharToken
		And there should be no more tokens

	Scenario: empty string 2 empty chars
		Given the text is "  "
		When parsing it
		Then it should be parsed into 1 tokens
		And the token should be " ", that is WhiteCharToken
		And there should be no more tokens

	Scenario: empty string 3 tabs
		Given the text is "			"
		When parsing it
		Then it should be parsed into 1 tokens
		And the token should be "	", that is WhiteCharToken
		And there should be no more tokens

	Scenario: single alphabetic words
		Given the text is "Hello"
		When parsing it
		Then it should be parsed into 1 tokens
		And the token should be "Hello", that is WordToken
		And there should be no more tokens

	Scenario: single alphabetic words both sides Un-striped
		Given the text is "  Hello  "
		When parsing it
		Then it should be parsed into 3 tokens
		And the token should be " ", that is WhiteCharToken
		And the token should be "Hello", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And there should be no more tokens

	Scenario: single alphabetic words right side Un-striped
		Given the text is "Hello  "
		When parsing it
		Then it should be parsed into 2 tokens
		And the token should be "Hello"
		And the token should be " ", that is WhiteCharToken
		And there should be no more tokens

	Scenario: single alphabetic words left side Un-striped
		Given the text is "  Hello"
		When parsing it
		Then it should be parsed into 2 tokens
		And the token should be " ", that is WhiteCharToken
		And the token should be "Hello", that is WordToken
		And there should be no more tokens

	Scenario: double word sentence
		Given the text is "Hello  World"
		When parsing it
		Then it should be parsed into 3 tokens
		And the token should be "Hello", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "World", that is WordToken
		And there should be no more tokens

	Scenario: Un-striped double word sentences
		Given the text is " 	 Hello 	 World 	 "
		When parsing it
		Then it should be parsed into 5 tokens
		And the token should be " ", that is WhiteCharToken
		And the token should be "Hello", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "World", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And there should be no more tokens

	Scenario: only alphabet words
		Given the text is "   aa   bb   cc   "
		When parsing it
		Then it should be parsed into 7 tokens
		And the token should be " ", that is WhiteCharToken
		And the token should be "aa", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "bb", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "cc", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And there should be no more tokens

	Scenario: only numeric words
		Given the text is "  11 22 33 44  "
		When parsing it
		Then it should be parsed into 9 tokens
		And the token should be " ", that is WhiteCharToken
		And the token should be "11", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "22", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "33", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "44", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And there should be no more tokens

	Scenario: numeric and alphabetic words
		Given the text is "  aa 22 bb 44  cc  "
		When parsing it
		Then it should be parsed into 11 tokens
		And the token should be " ", that is WhiteCharToken
		And the token should be "aa", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "22", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "bb", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "44", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "cc", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And there should be no more tokens
