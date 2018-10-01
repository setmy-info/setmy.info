Feature: Example REST Solution

    Scenario: Public resource
        Given REST resource /rest/public
        When getting person by first name Imre and last name Tabur
        Then result firstName should contain Imre
        Then result lastName should contain Tabur
