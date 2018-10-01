'use strict';

webAppDirectives.directive('applicationVersion', function (CODENAME, VERSION, $log) {
    return function (scope, element, attributes) {
        $log.debug('CODENAME: ', CODENAME, 'VERSION', VERSION);
        element.text(CODENAME + ': ' + VERSION);
    };
});
