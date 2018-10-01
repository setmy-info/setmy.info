'use strict';

webAppControllers.classy.controller({
    name: 'SessionUserDirectiveController',
    inject: ['$scope', 'timersService', 'configurationService', 'timersService'],
    models: ['session'],
    init: function () {
        this.initialize();
    },
    methods: {
        initialize: function () {
            this.newInterval();
            this.$scope.interval.start();
            this.$scope.minutesLeft = this.configurationService.config.defaultSessionTimeoutMinutes - webApplication.minutesIdle;
        },
        newInterval: function () {
            var that = this;
            this.$scope.interval = this.timersService.newInterval(function () {
                that.intervalExecition();
            }, this.timersService.SECOND);
        },
        intervalExecition: function () {
            this.$scope.minutesLeft = this.configurationService.config.defaultSessionTimeoutMinutes - webApplication.minutesIdle;
        }
    }
});

webAppDirectives.directive('sessionUser', [function () {
        return {
            restrict: 'A',
            templateUrl: directivesDirectory + '/sessionUserDirective.html',
            controller: 'SessionUserDirectiveController'
        };
    }]);
