'use strict';

describe('Mathematics', function () {

    var math;//Item under test

    beforeEach(function () {
        math = new Mathematics();
    });

    it("instance should exist before tests", function () {
        // Just for example. Usually not needed.
        expect(math).toBeDefined();
    });

    it("boolean true should be true", function () {
        // Just for example
        expect(true).toBe(true);
    });

    describe('function', function () {

        describe('add', function () {
            it("should add two numbers", function () {
                expect(math.add(0, 0)).toBe(0);
                expect(math.add(1, 1)).toBe(2);
                expect(math.add(2, 3)).toBe(5);
            });
        });
    });
});
