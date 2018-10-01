'use strict';

webAppViews.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/login', {
            templateUrl: 'components/views/login/login.html',
            controller: 'LoginController'
        });
    }]);

webApplication.classy.controller({
    name: 'LoginController',
    inject: ['$scope', '$location', 'authenticationService', 'configurationService', 'cookiesService'],
    init: function () {
        this.initialize();
    },
    methods: {
        initialize: function () {
            this.loadUserName();
        },
        loadUserName: function () {
            this.$scope.userName = this.cookiesService.getLastUserName();
        },
        submit: function () {
            this.login();
        },
        login: function () {
            this.authenticationService.login(this.$scope.userName, this.$scope.userPassword);
        }
    }
});
