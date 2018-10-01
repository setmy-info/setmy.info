'use strict';

webAppServices.factory('authenticationService', function (sessionService, $q, $log, configurationService, $location, Session) {

    var authenticationService = {
        login: function (userName, userPassword) {
            var authentication = this.authenticate({userName: userName, userPassword: userPassword});
            authentication.then(function (user) {
                sessionService.setUser(user);
                sessionService.startSession();
            }, function (reason) {
                $log.error('Login failed with reason: ', reason);
            }, function (user) {
                $log.debug('Got user: ', user);
            });
            var url = configurationService.getDefaultAuthenticatedUserViewURL();
            $location.path(url);
        },
        authenticate: function (user) {
            var deferred = $q.defer();
            //TODO : remove emulated login part ad replace with real authenticate functionality
            Session.get({getTask: 'login'}, function (backendUser) {
                deferred.notify(user);
                if (user.userName === backendUser.userName && user.userPassword === 'g6p8') {
                    deferred.resolve(backendUser);
                } else {
                    deferred.reject('Wrong user name or password!');
                }
            });
            return deferred.promise;
        }
    };

    return authenticationService;
});
