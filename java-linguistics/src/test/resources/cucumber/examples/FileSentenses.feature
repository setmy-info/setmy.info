Feature: sentences.txt

	Scenario: simple sentence from file
		Given text from row 1
		When parsing it
		Then it should be parsed into 4 tokens
		And the token should be "Tere"
		And the token should be " "
		And the token should be "maailm"
		And the token should be "!"
		And there should be no more tokens
