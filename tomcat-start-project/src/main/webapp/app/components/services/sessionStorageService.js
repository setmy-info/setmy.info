'use strict';

webAppServices.factory('sessionStorageService', function (localStorageService, configurationService) {

    var sessionStorageService = {
        storage: sessionStorage,
        get: localStorageService.get,
        set: localStorageService.set
    };

    return sessionStorageService;
});
