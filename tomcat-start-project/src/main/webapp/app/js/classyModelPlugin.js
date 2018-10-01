/*
 * By: https://gist.github.com/odiak/5df72e172ee312e96b0a
 */
(function () {
    angular.module('classy.modelPlugin', ['classy.core']).classy.plugin.controller({
        name: 'modelPlugin',
        options: {
            enabled: true
        },
        localInject: ['modelService'],
        init: function (klass, deps) {
            deps.$scope.translations = this.modelService.translations;
            klass.modelService = this.modelService;
            deps.$scope.modelService = this.modelService;
            if (klass.models) {
                angular.forEach(klass.models, function (value) {
                    this[value] = this.modelService[value];
                }, deps.$scope);
            }
            if (this.modelService[klass.name]) {//OK
                deps.$scope.model = this.modelService[klass.name];
            }
        }
    });
})();
