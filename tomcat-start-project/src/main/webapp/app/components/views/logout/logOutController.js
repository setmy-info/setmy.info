'use strict';

webAppViews.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/logout', {
            template: '',
            controller: 'LogOutController'
        });
    }]);

webApplication.classy.controller({
    name: 'LogOutController',
    inject: ['$scope', '$location', 'configurationService', 'sessionService'],
    init: function () {
        this.logout();
    },
    methods: {
        logout: function () {
            this.sessionService.logout();
        }
    }
});
