"use strict";

describe('serviceB', function () {

    var serviceB;

    beforeEach(function () {
        serviceB = jsdi.get().serviceB;
    });

    it('should be created for testing', function () {
        expect(serviceB).toBeDefined();
    });

    it('should return text for self and sub service', function () {
        expect(serviceB.getName()).toEqual("serviceB serviceC");
    });
});
