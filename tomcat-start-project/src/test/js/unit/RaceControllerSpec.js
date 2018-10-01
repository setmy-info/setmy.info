'use strict';

describe('RaceController', function () {

    var $controller, raceController, CONTROLLER_NAME = "RaceController";

    beforeEach(function () {
        module('angularApplication');
    });

    describe('function', function () {

        var raceService;

        beforeEach(inject(function (_$controller_, _raceService_) {
            $controller = _$controller_;
            raceService = _raceService_;
        }));

        describe('init (or page visit)', function () {

            it('should get list of car and competitor pairs', function () {
                raceController = $controller(CONTROLLER_NAME, {$scope: {}});
                spyOn(raceService, 'getCarCompetitorList');

                raceController.init();

                expect(raceService.getCarCompetitorList).toHaveBeenCalled();
            });

            it('should set list of car and competitor pairs into the scope', function () {
                var $scope = {};
                raceController = $controller(CONTROLLER_NAME, {$scope: $scope});
                var carCompetitorList = [{car: {id: 12345}}];
                raceService.getCarCompetitorList = function (setCarCompetitorList) {//emulating/replacing getCarCompetitorList
                    setCarCompetitorList(carCompetitorList);
                };

                raceController.init();//Real call/execute

                expect($scope.carCompetitorList).toBeDefined();
                expect($scope.carCompetitorList).toBe(carCompetitorList);
            });
        });
    });
});