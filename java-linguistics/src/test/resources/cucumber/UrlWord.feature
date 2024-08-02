Feature: URL-s as word

	Scenario: striped url
		Given the text is "https://example.com/url/atr?abc=123%2C"
		When parsing it
		Then it should be parsed into 17 tokens
		And the token should be "https"
		And the token should be ":"
		And the token should be "/"
		And the token should be "/"
		And the token should be "example"
		And the token should be "."
		And the token should be "com"
		And the token should be "/"
		And the token should be "url"
		And the token should be "/"
		And the token should be "atr"
		And the token should be "?"
		And the token should be "abc"
		And the token should be "="
		And the token should be "123"
		And the token should be "%"
		And the token should be "2C"
		And there should be no more tokens

	Scenario: non striped url
		Given the text is " https://example.com/url/atr?abc=123%2C  "
		When parsing it
		Then it should be parsed into 19 tokens
		And the token should be " "
		And the token should be "https"
		And the token should be ":"
		And the token should be "/"
		And the token should be "/"
		And the token should be "example"
		And the token should be "."
		And the token should be "com"
		And the token should be "/"
		And the token should be "url"
		And the token should be "/"
		And the token should be "atr"
		And the token should be "?"
		And the token should be "abc"
		And the token should be "="
		And the token should be "123"
		And the token should be "%"
		And the token should be "2C"
		And the token should be " "
		And there should be no more tokens

	Scenario: striped url with comma
		Given the text is "https://example.com/url/atr?abc=123%2C,"
		When parsing it
		Then it should be parsed into 18 tokens
		And the token should be "https", that is WordToken
		And the token should be ":", that is PhraseSeparatorToken
		And the token should be "/", that is OtherTextualCharacterToken
		And the token should be "/", that is OtherTextualCharacterToken
		And the token should be "example", that is WordToken
		And the token should be ".", that is SentenceEndingToken
		And the token should be "com", that is WordToken
		And the token should be "/", that is OtherTextualCharacterToken
		And the token should be "url", that is WordToken
		And the token should be "/", that is OtherTextualCharacterToken
		And the token should be "atr", that is WordToken
		And the token should be "?", that is SentenceEndingToken
		And the token should be "abc", that is WordToken
		And the token should be "=", that is OtherTextualCharacterToken
		And the token should be "123", that is WordToken
		And the token should be "%", that is OtherTextualCharacterToken
		And the token should be "2C", that is WordToken
		And the token should be ",", that is PhraseSeparatorToken
		And there should be no more tokens

	Scenario: non striped url with comma
		Given the text is "  https://example.com/url/atr?abc=123%2C,  "
		When parsing it
		Then it should be parsed into 20 tokens
		And the token should be " ", that is WhiteCharToken
		And the token should be "https", that is WordToken
		And the token should be ":", that is PhraseSeparatorToken
		And the token should be "/", that is OtherTextualCharacterToken
		And the token should be "/", that is OtherTextualCharacterToken
		And the token should be "example", that is WordToken
		And the token should be ".", that is SentenceEndingToken
		And the token should be "com", that is WordToken
		And the token should be "/", that is OtherTextualCharacterToken
		And the token should be "url", that is WordToken
		And the token should be "/", that is OtherTextualCharacterToken
		And the token should be "atr", that is WordToken
		And the token should be "?", that is SentenceEndingToken
		And the token should be "abc", that is WordToken
		And the token should be "=", that is OtherTextualCharacterToken
		And the token should be "123", that is WordToken
		And the token should be "%", that is OtherTextualCharacterToken
		And the token should be "2C", that is WordToken
		And the token should be ",", that is PhraseSeparatorToken
		And the token should be " ", that is WhiteCharToken
		And there should be no more tokens
