'use strict';

describe('nameService', function () {

    beforeEach(module('angularApplication'));

    var nameService;

    beforeEach(function () {
        var $injector = angular.injector(['angularApplication']);
        nameService = $injector.get('nameService');
    });

    it('should contain a behind ID 1', inject(function (nameService) {
        var person = nameService.getPerson(1);
        expect(nameService).not.toBeUndefined();
        expect(person).not.toBeUndefined();
        expect(person.firstName).toBe("Peeter");
        expect(person.lastName).toBe("Esimene");
    }));

    it('should contain a behind ID 2', inject(function (nameService) {
        var person = nameService.getPerson(2);
        expect(nameService).not.toBeUndefined();
        expect(person).not.toBeUndefined();
        expect(person.firstName).toBe("Jaana");
        expect(person.lastName).toBe("Lind");
    }));
});
