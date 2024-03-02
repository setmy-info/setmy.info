Feature: sentences.txt

	Background:
		Given file "sentences.txt" is read in as lines in list

	Scenario: Hello World in estonian
		Given the text is "Tere maailm!"
		When parsing it
		Then it should be parsed into 4 tokens
		And the token should be "Tere", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "maailm", that is WordToken
		And the token should be "!", that is SentenceEndingToken
		And there should be no more tokens

	Scenario: EKI organization sentence
		Given the text is "Eesti Keele Instituut on riiklik teadus- ja  arendusasutus."
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
		Given the text is "Tulekahju avastanud isik(ud), teis(t)ele võimalus(t)ele, ega ta (ei) tule."
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
