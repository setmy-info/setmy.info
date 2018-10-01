'use strict';

describe('SuperService', function () {

    var superService;//Item under test

    beforeEach(function () {
        superService = new SuperService();
    });

    describe('protocol', function () {

        describe('talk', function () {

            it("should make text from speech spoken", function () {
                var result = superService.talk();
                expect(result).toBe('This is Ground Control to Major Tom! This is Major Tom to Ground Control!');
            });

            it("should speak and listen", function () {
                //Spy functions calls on object superService
                spyOn(superService, 'speak').and.returnValue("Hello");
                spyOn(superService, 'listen').and.returnValue("World");

                //Make real call and execution
                var result = superService.talk();

                //Check time
                expect(superService.speak).toHaveBeenCalled();
                expect(superService.speak).toHaveBeenCalledWith("This is Ground Control to Major Tom");
                expect(superService.listen).toHaveBeenCalled();
                expect(result).toBe('Hello! World!');
            });
        });
    });
});
