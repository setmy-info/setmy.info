'use strict';

/* 
 https://github.com/angular/protractor/blob/master/docs/toc.md 
 http://angular.github.io/protractor/#/tutorial
 http://angular.github.io/protractor/#/toc
 http://angular.github.io/protractor/#/browser-support
 http://angular.github.io/protractor/#/locators
 */

var utilsModule = require('./utils');

describe('angular-seed application', function () {

    var utils = utilsModule.instance(protractor, browser, element, by);

    describe('index.html', function () {

        beforeEach(function () {
            utils.get('index.html');
        });

        it('should forward and open /view1 when opening index page', function () {
            expect(browser.getLocationAbsUrl()).toMatch("/view1");
        });
    });

    describe('login', function () {

        beforeEach(function () {
            browser.get('#/login');
        });

        it('should have login url', function () {
            expect(browser.getLocationAbsUrl()).toMatch("/login");
            expect(browser.getTitle()).toEqual('AngularJS Seed App');
        });


        it('should have login url', function () {
            utils.css('#loginInput').getAttribute('value').then(function (text) {
                expect(text).toBe('Valmis');
            });
        });

        it('should have login url', function () {
            var loginButton = utils.id('loginInput');//utils.css('#loginInput');
            var userName = utils.id('userName');//utils.css('#userName');
            var userPassword = utils.id('userPassword');//utils.css('#userPassword');
            var languageSelectorSelect = utils.css('#languageSelectorSelect option');//utils.css('#languageSelectorSelect');

            languageSelectorSelect.click();

            expect(loginButton.getAttribute('value')).toBe('Valmis');
            /*loginButton.getAttribute('value').then(function (text) {
             nameInput.sendKeys(name);
             expect(text).toBe('Valmis');
             });*/

            userName.sendKeys('user');
            userPassword.sendKeys('secret');
            loginButton.click().then(function () {
                expect(browser.getLocationAbsUrl()).toMatch("/view2");
            });
        });
    });
});
