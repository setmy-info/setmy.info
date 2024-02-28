Feature: old and new uncovered tests

	Scenario: difficult sentence 1
		Given the text is "Aaa, bbb (ccc, ddd, eee), fff (ggg; hhh; iii), jjj - kkk, lll mmm nnn: \"ooo, ppp, rrr sss - ttt (uuu; vvv õõõ, äää ööö!)\". Üüü (xxx, yyy, zzz [aaa, bbb, ccc {ddd, eee, fff ggg hhh}])! Iii, jjj, kkk: lll, mmm nnn? Ooo: \"ppp rrr sss?\""
		When parsing it
    # TODO : make string correct by estonian language rules

	Scenario: difficult sentence 2
		Given the text is "2) seletavad ja täiendavad kiilmärkused, nt sõnaseletused, viited, nimede hääldus: brokoli (spargelkapsas); sajab kõikjal (välja arvatud saartel ~ välja arvatud saared); elamute (Pärnu, Sireli 17 ja 17a) lammutamine; Tambet Lepik (edaspidi: töövõtja); Ita (õieti Ilse) Ever; Nike (loe: naiki) jalatsid;"
		When parsing it
    # TODO : parsing () etc character correctly into words or separate tokens

	Scenario: difficult sentence 3
		Given the text is "tulekahju avastanud isik(ud), teis(t)ele võimalus(t)ele, ega ta (ei) tule."
		When parsing it
    # TODO : parsing () etc character correctly into words or separate tokens

	Scenario: difficult sentence 4
		Given the text is ".!?"
		When parsing it
    # TODO : decide, should it be word or separate tokens. Most probably one single word token, because it is continuous characters and should be handled in post processing/oparsing.

	Scenario: difficult sentence 5
		Given the text is "Kesk-Aasia elanik"
		When parsing it
		Then it should be parsed into 5 tokens
		And the token should be "Kesk"
		And the token should be "-"
		And the token should be "Aasia"
		And the token should be " "
		And the token should be "elanik"
		And there should be no more tokens

	Scenario: difficult sentence 6
		Given the text is "Hello, World"
		When parsing it
    # TODO : ...

	Scenario: difficult sentence 7
		Given the text is "  Hello,  World  "
		When parsing it
    # TODO : ...

	Scenario: difficult sentence 8
		Given the text is "  Hello  ,  World  "
		When parsing it
    # TODO : ...

	Scenario: difficult sentence 9
		Given the text is "Hello, World."
		When parsing it
    # TODO : ...

	Scenario: difficult sentence 10
		Given the text is "   Hello  ,   World   .  "
		When parsing it
    # TODO : ...

	Scenario: difficult sentence 11
		Given the text is "   Hello,    World.   "
		When parsing it
    # TODO : ...

	Scenario: difficult sentence 12
		Given the text is "11.23% of world population."
		When parsing it
    # TODO : ...

	Scenario: difficult sentence 13
		Given the text is "   10,11 % of world population.   "
		When parsing it
    # TODO : ...

	Scenario: difficult sentence 14
		Given the text is "This is longer sentence, with some sentence ending characters. Maybe text have questions? Maybe text ends with!"
		When parsing it
    # TODO : ...

#
#
#
#
#
#
#
#
