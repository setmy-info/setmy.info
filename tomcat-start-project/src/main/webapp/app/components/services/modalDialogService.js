'use strict';

webAppServices.factory('modalDialogService', function ($uibModal) {

    var service = {
    };

    service.dialog = function (template, controllerName, resolve) {
        return $uibModal.open({
            templateUrl: viewsDirectory + template,
            controller: controllerName,
            resolve: resolve,
            animation: true,
            size: null,
            keyboard: true,
            backdropClick: false//backdrop: true, //dialogFade: false,
        });
    };

    return service;
});
