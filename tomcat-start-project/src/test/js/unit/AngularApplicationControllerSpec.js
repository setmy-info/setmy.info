'use strict';

describe('AngularApplicationController', function () {

    var $controller, CONTROLLER_NAME = "AngularApplicationController";

    beforeEach(function () {
        module('angularApplication');
    });

    describe('real execution example', function () {

        var controller;

        beforeEach(inject(function (_$controller_) {
            $controller = _$controller_;
        }));

        describe('initialization', function () {

            // With those tests we prove (that code is working as planned,
            // designed, expected) that scope ID is used to get person from
            // service at initialization
            // Tests tels to as what in code should be "carved in stone"
            // Test is like popup - "Are you sure you vant to change production code? YES : NO"

            it('should set pre initialized scope variables', function () {
                var $scope = {};
                controller = $controller(CONTROLLER_NAME, {$scope: $scope});
                expect($scope.rotateId).toBe(1);
                expect($scope.id).not.toBeDefined();
                //expect($scope.person).not.toBeDefined(); // This doesn't belong here - it is proven in service tests
            });

            it('should set person by scope ID variable', function () {
                var $scope = {};
                $scope.id = 1;
                controller = $controller(CONTROLLER_NAME, {$scope: $scope});
                expect($scope.person).toBeDefined();
                expect($scope.person.firstName).toBe("Peeter");
                expect($scope.person.lastName).toBe("Esimene");
            });

            it('just service call example, getting second person', function () {
                var $scope = {};
                $scope.id = 2;
                controller = $controller(CONTROLLER_NAME, {$scope: $scope});
                expect($scope.person).toBeDefined();
                expect($scope.person.firstName).toBe("Jaana");
                expect($scope.person.lastName).toBe("Lind");
            });
        });

        describe('changePerson', function () {

            it('should replace user in scope in rotated form between two persons', function () {
                var $scope = {};
                controller = $controller(CONTROLLER_NAME, {$scope: $scope});

                //Is in initial state
                expect($scope.person).not.toBeDefined();
                expect($scope.rotateId).toBe(1);

                controller.changePerson();
                expect($scope.person).toBeDefined();
                expect($scope.person.firstName).toBe("Jaana");//Person behind ID = 2
                expect($scope.person.lastName).toBe("Lind");

                controller.changePerson();
                expect($scope.person).toBeDefined();
                expect($scope.person.firstName).toBe("Peeter");//Person behind ID = 1
                expect($scope.person.lastName).toBe("Esimene");
            });
        });
    });

    describe('mocked spy example', function () {

        var $scope, $rootScope, $location, nameService, controller;

        beforeEach(inject(function (_$rootScope_, _$controller_, _$location_, _nameService_) {
            $rootScope = _$rootScope_;
            $controller = _$controller_;
            $location = _$location_;
            nameService = _nameService_;
            $scope = $rootScope.$new();
            //Set Spy
            spyOn(nameService, 'getPerson').and.returnValue({firstName: "Jaana", lastName: "Lind"});//.and.returnValue({firstName: "Peeter", lastName: "Esimene"});;
        }));

        it('should have service injected', function () {
            expect(nameService).toBeDefined();
        });

        describe('setPerson', function () {
            it('should set person object from service by ID', function () {
                var $scope = {};
                controller = $controller(CONTROLLER_NAME, {$scope: $scope});
                // TODO
            });
        });

        describe('changePerson', function () {

            it('should get person object from service', function () {
                var $scope = {};
                controller = $controller(CONTROLLER_NAME, {$scope: $scope});
                spyOn(controller, 'setPerson');

                expect($scope.rotateId).toBe(1);

                controller.changePerson();

                expect($scope.person).toBeDefined();
                expect($scope.rotateId).toBe(2);
                expect(controller.setPerson).toHaveBeenCalledWith(2);
                expect($scope.person.firstName).toBe("Jaana");
                expect($scope.person.lastName).toBe("Lind");
            });

        });

        describe('at initialization', function () {

            it('should get person object from name service and set it to the scope', function () {
                var $scope = {};
                controller = $controller(CONTROLLER_NAME, {$scope: $scope});
                expect($scope.person).toBeDefined();
                expect($scope.person.firstName).toBe("Jaana");
                expect($scope.person.lastName).toBe("Lind");
                expect(nameService.getPerson).toHaveBeenCalled();
            });
        });
    });
});