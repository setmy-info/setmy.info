'use strict';

var utilsModule = require('./utils');

describe('angular-seed application', function () {

    var utils = utilsModule.instance(protractor, browser, element, by);
    var loginResult;

    describe('view2', function () {

        beforeEach(function () {
            utils.setUserName('user');
            utils.setUserPassword('secret');
            loginResult = utils.login();
        });

        it('should be oped after login', function () {
            loginResult.then(function () {
                expect(browser.getLocationAbsUrl()).toMatch("/view2");
                utils.logout();
            });
        });

        it('should open view1 after logout', function () {
            utils.logout().then(function () {
                expect(browser.getLocationAbsUrl()).toMatch("/view1");
            });
        });
    });
});
