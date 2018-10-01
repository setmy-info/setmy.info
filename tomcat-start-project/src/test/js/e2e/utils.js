module.exports = {
    instance: function (protractor, browser, element, by) {
        return {
            protractor: protractor,
            browser: browser,
            element: element,
            by: by,
            setUrl: function (url) {
                this.url = url;
            },
            setUserName: function (userName) {
                this.userName = userName;
            },
            setUserPassword: function (userPassword) {
                this.userPassword = userPassword;
            },
            get: function (url) {
                this.browser.get(url);
            },
            css: function (cssSelector) {
                return this.element(this.by.css(cssSelector));
            },
            id: function (id) {
                return this.element(this.by.id(id));
            },
            model: function (model) {
                return this.element(this.by.model(model));
            },
            binding: function (model) {
                return this.element(this.by.binding(model));
            },
            login: function () {
                browser.get('#/login');
                var loginButton = this.id('loginInput');//utils.css('#loginInput');
                var userName = this.id('userName');//utils.css('#userName');
                var userPassword = this.id('userPassword');
                userName.sendKeys(this.userName);
                userPassword.sendKeys(this.userPassword);
                return loginButton.click();
            },
            logout: function () {
                var logOutLink = this.id('logOutLink');
                return logOutLink.click();
            }
        };
    }
};