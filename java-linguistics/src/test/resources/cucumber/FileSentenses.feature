Feature: sentences.txt

	Background:
		Given file "sentences.txt" is read in as lines in list

	Scenario: Hello World in estonian
		Given text from row 1
		When parsing it
		Then it should be parsed into 4 tokens
		And the token should be "Tere", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "maailm", that is WordToken
		And the token should be "!", that is SentenceEndingToken
		And there should be no more tokens

	Scenario: EKI organization sentence
		Given text from row 2
		When parsing it
		Then it should be parsed into 17 tokens
		And the token should be "Eesti", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "Keele", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "Instituut", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "on", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "riiklik", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "teadus", that is WordToken
		And the token should be "-", that is PhraseSeparatorToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "ja", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "arendusasutus", that is WordToken
		And the token should be ".", that is SentenceEndingToken
		And there should be no more tokens

	Scenario: EKI parentheses usage sentence
		Given text from row 10
		When parsing it
		Then it should be parsed into 33 tokens
		And the token should be "Tulekahju"
		And the token should be " "
		And the token should be "avastanud"
		And the token should be " "
		And the token should be "isik"
		And the token should be "("
		And the token should be "ud"
		And the token should be ")"
		And the token should be ","
		And the token should be " "
		And the token should be "teis"
		And the token should be "("
		And the token should be "t"
		And the token should be ")"
		And the token should be "ele"
		And the token should be " "
		And the token should be "võimalus"
		And the token should be "("
		And the token should be "t"
		And the token should be ")"
		And the token should be "ele"
		And the token should be ","
		And the token should be " "
		And the token should be "ega"
		And the token should be " "
		And the token should be "ta"
		And the token should be " "
		And the token should be "("
		And the token should be "ei"
		And the token should be ")"
		And the token should be " "
		And the token should be "tule"
		And the token should be "."
		And there should be no more tokens

	Scenario: EKI hyphen usage
		Given text from row 11
		When parsing it
		Then it should be parsed into 5 tokens
		And the token should be "Kesk"
		And the token should be "-"
		And the token should be "Aasia"
		And the token should be " "
		And the token should be "elanik"
		And there should be no more tokens

	Scenario: Hello, World!
		Given text from row 12
		When parsing it
		Then it should be parsed into 5 tokens
		And the token should be "Hello", that is WordToken
		And the token should be ",", that is PhraseSeparatorToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "World", that is WordToken
		And the token should be "!", that is SentenceEndingToken
		And there should be no more tokens
