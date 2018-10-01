'use strict';

var webApplication = angular.module('webApplication', [
    'ngRoute',
    'ngCookies',
    'ngResource',
    'ngMessages',
    'ngSanitize',
    'ngLocale',
    'ui.bootstrap',
    'ui.bootstrap.modal',
    'classy',
    'classy.modelPlugin',
    'AngularAOP',
    'tmh.dynamicLocale',
    'webApplication.views',
    'webApplication.values',
    'webApplication.controllers',
    'webApplication.directives',
    'webApplication.filters',
    'webApplication.resources',
    'webApplication.services'
]),
        debugKey = 'debug',
        componentsDirectory = 'components',
        directivesDirectory = componentsDirectory + '/directives',
        viewsDirectory = componentsDirectory + '/views',
        webAppViews = angular.module('webApplication.views', ['ngRoute']),
        webAppValues = angular.module('webApplication.values', ['webApplication.filters', 'webApplication.directives']),
        webAppControllers = angular.module('webApplication.controllers', ['classy', 'classy.modelPlugin', 'ngLocale']),
        webAppDirectives = angular.module('webApplication.directives', []),
        webAppFilters = angular.module('webApplication.filters', []),
        webAppResources = angular.module('webApplication.resources', []),
        webAppServices = angular.module('webApplication.services', []);

webAppServices.getNames = function () {
    if (!webAppServices.serviceNames) {
        webAppServices.serviceNames = [];
        var names = webAppServices.serviceNames, elements = webAppServices._invokeQueue, length = elements.length, i, element, args, name;
        for (i = 0; i < length; i++) {
            element = elements[i];
            if (element[0] === '$provide' && element[1] === 'factory') {
                args = element[2];
                name = args[0];
                names.push(name);
            }
        }
    }
    return webAppServices.serviceNames;
};

webAppServices.getServices = function () {
    if (!webAppServices.services) {
        webAppServices.services = {
        };
        var i, serviceName, serviceNames = webAppServices.getNames(), length = serviceNames.length;
        webAppServices.services.serviceNames = serviceNames;
        for (i = 0; i < length; i++) {
            serviceName = serviceNames[i];
            webAppServices.services[serviceName] = angular.getService(serviceName);
        }
    }
    return webAppServices.services;
};

webApplication.run(function (initService) {
    initService.init();
});

webApplication.minutesIdle = 0;

webApplication.bodyOnLoad = function () {
    setInterval(function () {
        webApplication.minutesIdle++;
    }, 60000);
};

webApplication.bodyOnBlur = function () {//Tab changes!
};

webApplication.bodyOnKeyDown = function () {
    webApplication.minutesIdle = 0;
};

webApplication.bodyOnMouseDown = function () {
    webApplication.minutesIdle = 0;
};

webApplication.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.otherwise({redirectTo: '/view1'});
    }]);

webApplication.config(['$logProvider', function ($logProvider) {//To enable logging: in browser JS debugger console: localStorage.setItem('debug','true');
        $logProvider.debugEnabled(JSON.parse(localStorage.getItem(debugKey) || 'false'));
    }]);

webApplication.config(function (tmhDynamicLocaleProvider) {
    tmhDynamicLocaleProvider.localeLocationPattern('lib/angular-i18n/angular-locale_{{locale}}.js');
});

webApplication.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.interceptors.push('httpInterceptorService');
    }]);

angular.getInjectorElement = function () {
    if (!angular.injectorElement) {
        angular.injectorElement = angular.element(document.body);
    }
    return angular.injectorElement;
};

angular.getService = function (serviceName) {
    return angular.getInjectorElement().injector().get(serviceName);
};

angular.getServices = function () {
    return webAppServices.getServices();
};

angular.getLog = function () {
    return angular.getService('$log');
};