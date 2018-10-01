'use strict';

webAppServices.factory('cookiesService', function ($cookies, configurationService) {

    var cookiesService = {
        $cookies: $cookies
    };

    cookiesService.getLastUserName = function () {
        return this.$cookies.get(configurationService.USER_NAME_COOKIE_KEY);
    };

    cookiesService.setLastUserName = function (userName) {
        var future = new Date();
        future.setUTCFullYear(future.getUTCFullYear() + configurationService.config.defaultUserNameCookieExpireYears);
        this.$cookies.put(configurationService.USER_NAME_COOKIE_KEY, userName, {expires: future});
    };

    cookiesService.removeSessionId = function () {
        this.remove(configurationService.SESSIONID_COOKIE_KEY);
    };

    cookiesService.remove = function (key) {
        this.$cookies.remove(key);
    };

    return cookiesService;
});
