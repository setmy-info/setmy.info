'use strict';

describe('raceService', function () {

    beforeEach(module('angularApplication'));

    var raceService, carService, competitorService;
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
        beforeEach(inject(function (_raceService_, _carService_, _competitorService_) {
            raceService = _raceService_;
            carService = _carService_;
            competitorService = _competitorService_;
        }));

        describe('getCarCompetitorList', function () {

            it('should bind together cars and copetitors data', function () {
                var carCompetitorList = null;
                var setCarCompetitorList = function (result) {
                    carCompetitorList = result;
                };
                carService.getCars = function (setCars) {
                    setCars(cars);
                };
                competitorService.getCompetitors = function (setCompetitors) {
                    setCompetitors(competitors);
                };

                raceService.getCarCompetitorList(setCarCompetitorList);//Real call

                expect(carCompetitorList[0].car).toBe(cars[0]);// Checking all cars
                expect(carCompetitorList[1].car).toBe(cars[1]);
                expect(carCompetitorList[0].competitor).toBe(competitors[1]);
                expect(carCompetitorList[1].competitor).toBe(competitors[0]);
            });
        });
    });
});
