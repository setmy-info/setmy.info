'use strict';

webAppServices.factory('localStorageService', function () {

    var localStorageService = {
        storage: localStorage
    };

    localStorageService.get = function (key) {
        var storageValue = this.storage.getItem(key);
        if (storageValue) {
            return JSON.parse(storageValue);
        }
        return null;
    };

    localStorageService.set = function (key, object) {
        if (object) {
            this.storage.setItem(key, JSON.stringify(object));
        }
    };

    return localStorageService;
});
