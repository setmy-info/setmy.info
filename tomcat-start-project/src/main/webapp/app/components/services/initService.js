'use strict';

webAppServices.factory('initService', function (sessionService, schedulerService) {

    var initService = {
    };

    initService.init = function () {
        sessionService.reguestUser();
        schedulerService.init();
    };

    return initService;
});
