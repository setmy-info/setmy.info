'use strict';

/* https://github.com/angular/protractor/blob/master/docs/getting-started.md 
 * https://github.com/karma-runner/grunt-karma
 * */

describe('my app', function() {

  browser.get('dictionary.html');

  it('should automatically redirect to /login when location hash/fragment is empty', function() {
    expect(browser.getLocationAbsUrl()).toMatch("/login");
  });


  describe('login', function() {

    beforeEach(function() {
      browser.get('dictionary.html#/login');
    });


    it('should render view1 when user navigates to /login', function() {
      expect(element.all(by.css('[ng-view] p')).first().getText()).
        toMatch(/partial for view 1/);
    });

  });


  /*describe('view2', function() {

    beforeEach(function() {
      browser.get('index.html#/view2');
    });


    it('should render view2 when user navigates to /view2', function() {
      expect(element.all(by.css('[ng-view] p')).first().getText()).
        toMatch(/partial for view 2/);
    });

  });*/
});
