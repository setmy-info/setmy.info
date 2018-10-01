'use strict';

describe('webApplication.view1 module', function () {

    beforeEach(module('webApplication'));

    describe('view1 controller', function () {

        var injects = {
        }, controllerName = 'View1Controller';

        it('should ....', inject(function ($controller, $rootScope, modelService) {
            injects.$scope = $rootScope.$new();
            injects.modelService = modelService;
            var view1Controller = $controller(controllerName, injects);
            expect(view1Controller).toBeDefined();
        }));

    });
});