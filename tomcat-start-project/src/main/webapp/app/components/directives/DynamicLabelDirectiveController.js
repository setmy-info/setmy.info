angularApplication.classy.controller({
    name: 'DynamicLabelDirectiveController',
    inject: ['$scope', '$resource'],
    data: {
        mode: "edit"
    },
    init: function () {
        this.$scope.mode = "edit";
    },
    methods: {
    }
});
/*
 //var angularApplication = angular.module('exampleApp', []);
 exampleApp.controller('LabelDirectiveController', ['$scope', '$resource', function ($scope, $resource) {
 }]);
 */
