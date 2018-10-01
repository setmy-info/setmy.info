angularApplication.directive('dynamicLabelDirective', [function () {
        return {
            require: 'dynamicLabelDirective',
            restrict: 'A',
            templateUrl: 'directives/DynamicLabelDirective.html',
            scope: {
                content: '=?',
                mode: '=?'
            }
        };
    }]);
