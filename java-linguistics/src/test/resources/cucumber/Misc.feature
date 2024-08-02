Feature: old and new uncovered tests

	'''
	Miscellaneous test steps.
	'''

	Scenario: difficult sentence 1
		Given the text is "Aaa, bbb (ccc, ddd, eee), fff (ggg; hhh; iii), jjj - kkk, lll mmm nnn: \"ooo, ppp, rrr sss - ttt (uuu; vvv õõõ, äää ööö!)\". Üüü (xxx, yyy, zzz [aaa, bbb, ccc {ddd, eee, fff ggg hhh}])! Iii, jjj, kkk: lll, mmm nnn? Ooo: \"ppp rrr sss?\""
		When parsing it
    # TODO : make string correct by estonian language rules

	Scenario: difficult sentence 2
		Given the text is "2) seletavad ja täiendavad kiilmärkused, nt sõnaseletused, viited, nimede hääldus: brokoli (spargelkapsas); sajab kõikjal (välja arvatud saartel ~ välja arvatud saared); elamute (Pärnu, Sireli 17 ja 17a) lammutamine; Tambet Lepik (edaspidi: töövõtja); Ita (õieti Ilse) Ever; Nike (loe: naiki) jalatsid;"
		When parsing it
    # TODO : parsing () etc character correctly into words or separate tokens

	Scenario: difficult sentence 4
		Given the text is ".!?"
		When parsing it
		Then it should be parsed into 3 tokens
		And the token should be ".", that is SentenceEndingToken
		And the token should be "!", that is SentenceEndingToken
		And the token should be "?", that is SentenceEndingToken
		And there should be no more tokens

	Scenario: difficult sentence 6
		Given the text is "Hello, World"
		When parsing it
		Then it should be parsed into 4 tokens
		And the token should be "Hello"
		And the token should be ","
		And the token should be " "
		And the token should be "World"
		And there should be no more tokens

	Scenario: difficult sentence 7
		Given the text is "   Hello,   World   "
		When parsing it
		Then it should be parsed into 6 tokens
		And the token should be " ", that is WhiteCharToken
		And the token should be "Hello", that is WordToken
		And the token should be ",", that is PhraseSeparatorToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "World", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And there should be no more tokens

	Scenario: difficult sentence 8
		Given the text is "   Hello   ,   World   "
		When parsing it
		Then it should be parsed into 7 tokens
		And the token should be " ", that is WhiteCharToken
		And the token should be "Hello", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be ",", that is PhraseSeparatorToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "World", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And there should be no more tokens

	Scenario: difficult sentence 10
		Given the text is "   Hello  ,   World   .  "
		When parsing it
		Then it should be parsed into 9 tokens
		And the token should be " ", that is WhiteCharToken
		And the token should be "Hello", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be ",", that is PhraseSeparatorToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "World", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be ".", that is SentenceEndingToken
		And the token should be " ", that is WhiteCharToken
		And there should be no more tokens

	Scenario: difficult sentence 11
		Given the text is "   Hello,   World.   "
		When parsing it
		Then it should be parsed into 7 tokens
		And the token should be " ", that is WhiteCharToken
		And the token should be "Hello", that is WordToken
		And the token should be ",", that is PhraseSeparatorToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "World", that is WordToken
		And the token should be ".", that is SentenceEndingToken
		And the token should be " ", that is WhiteCharToken
		And there should be no more tokens

	Scenario: difficult sentence 12
		Given the text is "11.23% of world population."
		When parsing it
		Then it should be parsed into 11 tokens
		And the token should be "11", that is WordToken
		And the token should be ".", that is SentenceEndingToken
		And the token should be "23", that is WordToken
		And the token should be "%", that is OtherTextualCharacterToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "of", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "world", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "population", that is WordToken
		And the token should be ".", that is SentenceEndingToken
		And there should be no more tokens

	Scenario: difficult sentence 13
		Given the text is "   10,11 % of world population.   "
		When parsing it
		Then it should be parsed into 14 tokens
		And the token should be " ", that is WhiteCharToken
		And the token should be "10", that is WordToken
		And the token should be ",", that is PhraseSeparatorToken
		And the token should be "11", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "%", that is OtherTextualCharacterToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "of", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "world", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "population", that is WordToken
		And the token should be ".", that is SentenceEndingToken
		And the token should be " ", that is WhiteCharToken
		And there should be no more tokens

	Scenario: difficult sentence 14
		Given the text is "This is longer sentence, with some sentence ending characters. Maybe text have questions? Maybe text ends with!"
		When parsing it
		And the token should be "This", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "is", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "longer", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "sentence", that is WordToken
		And the token should be ",", that is PhraseSeparatorToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "with", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "some", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "sentence", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "ending", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "characters", that is WordToken
		And the token should be ".", that is SentenceEndingToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "Maybe", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "text", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "have", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "questions", that is WordToken
		And the token should be "?", that is SentenceEndingToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "Maybe", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "text", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "ends", that is WordToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "with", that is WordToken
		And the token should be "!", that is SentenceEndingToken
		And there should be no more tokens

	Scenario: EKI hyphen usage
		Given the text is "Kesk-Aasia elanik"
		When parsing it
		Then it should be parsed into 5 tokens
		And the token should be "Kesk"
		And the token should be "-"
		And the token should be "Aasia"
		And the token should be " "
		And the token should be "elanik"
		And there should be no more tokens

	Scenario: Hello, World!
		Given the text is "Hello, World!"
		When parsing it
		Then it should be parsed into 5 tokens
		And the token should be "Hello", that is WordToken
		And the token should be ",", that is PhraseSeparatorToken
		And the token should be " ", that is WhiteCharToken
		And the token should be "World", that is WordToken
		And the token should be "!", that is SentenceEndingToken
		And there should be no more tokens
