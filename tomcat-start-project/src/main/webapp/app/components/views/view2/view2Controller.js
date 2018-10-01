'use strict';

webAppViews.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/view2', {
            templateUrl: 'components/views/view2/view2.html',
            controller: 'View2Controller'
        });
    }]);

webApplication.classy.controller({
    name: 'View2Controller',
    inject: ['$scope'],
    init: function () {
        this.$scope.date = new Date();
        this.$scope.format = "short";//"medium";//"dd.MM.yyyy hh:mm:ss";
    }
});
