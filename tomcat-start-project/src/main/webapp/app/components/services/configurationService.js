'use strict';

webAppServices.factory('configurationService', function (localStorageService) {

    var configurationService = {
        LANG: 'lang',
        SESSIONID_COOKIE_KEY: 'JSESSIONID',
        USER_NAME_COOKIE_KEY: 'userName',
        USER_STORAGE_KEY: 'user',
        config: {
            defaultAnonymousUserViewURL: 'view1',
            defaultAuthenticatedUserViewURL: 'view2',
            defaultUserNameCookieExpireYears: 5,
            defaultSessionTimeoutMinutes: 15,
            defaultTimeToLogOutMinutes: 1
        },
        getLanguage: function () {
            return localStorageService.get(this.LANG) || this.getDefaultLanguage();
        },
        setLanguage: function (lang) {
            localStorageService.set(this.LANG, lang);
        },
        getDefaultLanguage: function () {
            return "et";
        },
        getDefaultAnonymousUserViewURL: function () {
            return this.config.defaultAnonymousUserViewURL;
        },
        getDefaultAuthenticatedUserViewURL: function () {
            return this.config.defaultAuthenticatedUserViewURL;
        },
        validate: function () {
            if (configurationService.config.defaultTimeToLogOutMinutes >= configurationService.config.defaultSessionTimeoutMinutes) {
                throw 'Session time out is less than dialog visible time';
            }
        }
    };
    configurationService.validate();
    return configurationService;
});
