"use strict";

describe('serviceC', function () {

    var serviceC;

    beforeEach(function () {
        serviceC = jsdi.get().serviceC;
    });

    it('should be created for testing', function () {
        expect(serviceC).toBeDefined();
    });

    it('should return text for self', function () {
        expect(serviceC.getName()).toEqual("serviceC");
    });
});
