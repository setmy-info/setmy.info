'use strict';

webAppResources.factory('Version', function ($resource, JSON_RESOURCES_PREFIX) {
    //return $resource(JSON_RESOURCES_PREFIX + 'version/:number.json', null, null);
    return $resource(JSON_RESOURCES_PREFIX + 'version.json', null, null);
});

webAppResources.factory('Translations', function ($resource, JSON_RESOURCES_PREFIX) {
    return $resource(JSON_RESOURCES_PREFIX + '/translations.:lang.json', null, null);
});

webAppResources.factory('Ping', function ($resource, JSON_RESOURCES_PREFIX) {
    return $resource(JSON_RESOURCES_PREFIX + '/ping.json', null, null);
});

webAppResources.factory('Session', function ($resource, JSON_RESOURCES_PREFIX) {
    return $resource(JSON_RESOURCES_PREFIX + '/:getTask.json', null, null);
});

webAppResources.factory('Person', function ($resource, REST_RESOURCES_PREFIX) {
    return $resource(REST_RESOURCES_PREFIX + '/person/:personId', null, null);
});
