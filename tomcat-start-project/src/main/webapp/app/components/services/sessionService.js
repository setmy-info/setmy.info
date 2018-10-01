'use strict';

webAppServices.factory('sessionService', function (modelService, $uibModal, configurationService, timersService, cookiesService, $location, sessionStorageService, $log, Session) {

    var service = {
        dialogIdleTimeoutMinutes: configurationService.config.defaultSessionTimeoutMinutes - configurationService.config.defaultTimeToLogOutMinutes
    };

    service.reguestUser = function () {
        Session.get({getTask: 'session'}, function (backendUser) {
            $log.debug('reguestUser backendUser: ', backendUser);
            modelService.session.user = backendUser;
        });
    };

    service.setUser = function (user) {
        modelService.session.user = user;
        cookiesService.setLastUserName(user.userName);
    };

    service.haveUser = function () {
        return !!modelService.session.user;
    };

    service.logout = function () {
        modelService.session.user = null;
        this.logOutDialogInterval.stop();
        cookiesService.removeSessionId();
        Session.get({getTask: 'logout'}, function (data) {});
        var url = configurationService.getDefaultAnonymousUserViewURL();
        $location.path(url);
    };

    service.startSession = function () {
        this.logOutDialogInterval.start();
    };

    service.logOutDialogIntervalCallback = function () {
        $log.debug('logOutDialogIntervalCallback', webApplication.minutesIdle, this.dialogIdleTimeoutMinutes);
        if (webApplication.minutesIdle >= this.dialogIdleTimeoutMinutes) {
            this.openLogOutDialog();
        }
    };
    service.openLogOutDialog = function () {
        $log.debug('openLogOutDialog');
        var that = this;
        if (!this.dialogOpened) {
            this.dialog = $uibModal.open({
                animation: false,
                templateUrl: viewsDirectory + '/logout/logOutDialog.html',
                controller: 'LogOutDialogController',
                size: null,
                backdrop: false,
                keyboard: true
            });
            this.dialogOpened = true;
            this.logOutDialogInterval.stop();
            service.forcedLogoutTimer.start();
            this.dialog.result.then(function () {
                that.forcedLogoutTimer.stop();
                that.logOutDialogInterval.start();
                that.dialogOpened = false;
            }, function () {
                that.forcedLogoutTimer.stop();
                that.logout();
                that.dialogOpened = false;
            });
        }
        return this.dialog;
    };

    service.closeDialog = function () {
        if (this.dialogOpened) {
            this.dialog.dismiss();
        }
    };

    service.forcedLogOutTimerCallback = function () {
        $log.debug('newLogOutDialogInterval');
        this.stopForcedLogOutTimer();
        this.closeDialog();
    };

    service.stopForcedLogOutTimer = function () {
        this.forcedLogoutTimer.stop();
    };

    service.newLogOutDialogInterval = function () {
        $log.debug('newLogOutDialogInterval');
        var that = this;
        service.logOutDialogInterval = timersService.newInterval(function () {
            that.logOutDialogIntervalCallback();
        }, timersService.MINUTE);
    };

    service.newForcedLogoutTimer = function () {
        $log.debug('newForcedLogoutTimer');
        var that = this;
        service.forcedLogoutTimer = timersService.newTimer(function () {
            that.forcedLogOutTimerCallback();
        }, timersService.minutesToMillis(configurationService.config.defaultTimeToLogOutMinutes));
    };

    service.newLogOutDialogInterval();
    service.newForcedLogoutTimer();

    return service;
});
