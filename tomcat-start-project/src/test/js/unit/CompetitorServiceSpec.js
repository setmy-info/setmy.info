'use strict';

describe('competitorService', function () {

    beforeEach(module('angularApplication'));

    var carService, competitorService;
    var cars = [
        {
            "id": 1,
            "number": 13,
            "competitorId": 2
        },
        {
            "id": 2,
            "number": 14,
            "competitorId": 1
        }
    ];
    var competitors = [
        {
            "id": 1,
            "firstName": "Peeter",
            "lastName": "Esimene"
        },
        {
            "id": 2,
            "firstName": "Jaana",
            "lastName": "Lind"
        }
    ];

    describe('function', function () {
        beforeEach(inject(function (_carService_, _competitorService_) {
            carService = _carService_;
            competitorService = _competitorService_;
        }));

        describe('findCompetitorById', function () {

            it('should return null when empty competitors list is passed', function () {
                var result = competitorService.findCompetitorById([], 12345);
                expect(result).toBe(null);
            });

            it('should return null when empty competitors list is passed', function () {
                var result = competitorService.findCompetitorById([], 12345);
                expect(result).toBe(null);
            });

            it('should return null when null competitors list is passed', function () {
                var result = competitorService.findCompetitorById(null, 12345);
                expect(result).toBe(null);
            });

            it('should return null when competitor is not found', function () {
                var result = competitorService.findCompetitorById(competitors, 12345);
                expect(result).toBe(null);
            });

            it('should return object with same id as requested', function () {
                var expectedResult = competitors[1];
                var result = competitorService.findCompetitorById(competitors, expectedResult.id);
                expect(result).toBe(expectedResult);
            });
        });
    });
});
