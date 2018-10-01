'use strict';

describe('webApplication.view2 module', function () {

    beforeEach(module('webApplication'));

    var injects = {}, controllerName = 'View2Controller';

    describe('view2 controller', function () {

        it('should ....', inject(function ($controller, $rootScope) {
            injects.$scope = $rootScope.$new();
            var view2Controller = $controller(controllerName, injects);
            expect(view2Controller).toBeDefined();
        }));

    });
});