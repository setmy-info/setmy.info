Feature: URL-s as word

	Scenario: striped url
		Given the text is "https://example.com/url/atr?abs=123%2C"
		When parsing it
		Then it should be parsed into 1 tokens
		And the token should be "https://example.com/url/atr?abs=123%2C"
		And there should be no more tokens

	Scenario: non striped url
		Given the text is " https://example.com/url/atr?abs=123%2C  "
		When parsing it
		Then it should be parsed into 1 tokens
		And the token should be "https://example.com/url/atr?abs=123%2C"
		And there should be no more tokens

	Scenario: striped url with comma
		Given the text is "https://example.com/url/atr?abs=123,"
		When parsing it
		Then it should be parsed into 2 tokens
		And the token should be "https://example.com/url/atr?abs=123"
		And the token should be ","
		And there should be no more tokens

	Scenario: non striped url with comma
		Given the text is "  https://example.com/url/atr?abs=123,  "
		When parsing it
		Then it should be parsed into 2 tokens
		And the token should be "https://example.com/url/atr?abs=123"
		And the token should be ","
		And there should be no more tokens
