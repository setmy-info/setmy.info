'use strict';

describe('webApplication.version module', function () {
    
    beforeEach(module('webApplication'));

    describe('version service', function () {
        it('should return current version', inject(function (VERSION) {
            expect(VERSION).toEqual('0.1');
        }));
    });
});
