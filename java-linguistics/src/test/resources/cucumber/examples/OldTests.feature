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
    # TODO :
    And the token should be "Kesk-Aasia"
    And the token should be "elanik"
    And there should be no more tokens
