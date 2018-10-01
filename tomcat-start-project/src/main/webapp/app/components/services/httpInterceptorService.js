'use strict';

webAppServices.factory('httpInterceptorService', function ($q) {

    var service = {
        request: function (config) {
            // do something
            return config;
        },
        requestError: function (rejection) {
            // do something on error
            /*if (canRecover(rejection)) {
             return responseOrNewPromise;
             }*/
            return $q.reject(rejection);
        },
        response: function (response) {
            // do something on success
            return response;
        },
        responseError: function (rejection) {
            // do something on error
            /*if (canRecover(rejection)) {
             return responseOrNewPromise;
             }*/
            return $q.reject(rejection);
        }
    };

    return service;
});
