'use strict';

describe('webApplication.version module', function () {
    
    beforeEach(module('webApplication'));

    describe('interpolate filter', function () {
        beforeEach(module(function ($provide) {
            $provide.value('VERSION', 'TEST_VER');
        }));

        it('should replace VERSION', inject(function (interpolateFilter) {
            expect(interpolateFilter('before %VERSION% after')).toEqual('before TEST_VER after');
        }));
    });
});
