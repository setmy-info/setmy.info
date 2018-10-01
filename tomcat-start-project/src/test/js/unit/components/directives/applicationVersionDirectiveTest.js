'use strict';

describe('webApplication.version module', function () {
    
    beforeEach(module('webApplication'));

    describe('app-version directive', function () {
        it('should print current version', function () {
            module(function ($provide) {
                $provide.value('VERSION', 'TEST_VER');
            });
            inject(function ($compile, $rootScope) {
                var element = $compile('<span data-application-version></span>')($rootScope);
                expect(element.text()).toEqual('TEST_VER');
            });
        });
    });
});
