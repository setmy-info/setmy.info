'use strict';

describe('Water class', function () {

    var water;

    beforeEach(function () {
        water = new Water();
    });

    it("water forms can be in 3 forms", function () {
        expect(Array.isArray(Water.waterForms)).toBe(true);
        expect(Water.waterForms.length).toBe(3);
    });

    it("initWater should set forms", function () {
        water.initWater();
        expect(water.forms[0]).toBe("ice");
        expect(water.forms[1]).toBe("water");
        expect(water.forms[2]).toBe("vapor");
    });

    it("getInstance should give back same object all the time", function () {
        expect(Water.getInstance() === Water.getInstance()).toBe(true);
    });

    it("getInstance object forms should have same contents as pre made array", function () {
        expect(Water.getInstance().forms).toEqual(Water.waterForms);
    });

    it("initWater should make copy of array into another array", function () {
        expect(Water.getInstance().forms === Water.getInstance().forms).toBe(false);
    });
});
