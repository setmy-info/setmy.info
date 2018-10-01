'use strict';

webAppControllers.classy.controller({
    name: 'TabsPanelDirectiveController',
    inject: ['$scope'],
    models: ['session'],
    init: function () {
        this.$scope.tabs = [
            {
                name: 'Tab One',
                content: 'Content 1'
            },
            {
                name: 'Tab Two',
                content: 'Content 2'
            },
            {
                name: 'Tab Three',
                content: 'Content 3'
            }
        ];
        this.$scope.index = 1;
    },
    methods: {
        tabChange: function (index) {
            this.$scope.index = index;
        }
    }
});

webAppDirectives.directive('tabsPanel', [function () {
        return {
            restrict: 'A',
            templateUrl: directivesDirectory + '/tabsPanelDirective.html',
            controller: 'TabsPanelDirectiveController'
        };
    }]);
