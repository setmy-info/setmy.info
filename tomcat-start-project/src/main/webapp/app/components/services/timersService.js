'use strict';

webAppServices.factory('timersService', function ($timeout, $interval) {

    var timersService = {
    };

    timersService.MINUTE = 60000;
    timersService.SECOND = 1000;

    timersService.newTimer = function (callBack, delay) {
        return this.newTimerOrIntervalObject(callBack, delay, $timeout);
    };

    timersService.newInterval = function (callBack, delay) {
        return this.newTimerOrIntervalObject(callBack, delay, $interval);
    };

    timersService.newTimerOrIntervalObject = function (callBack, delay, service) {
        return {
            service: service,
            callBack: callBack,
            delay: delay,
            instance: null,
            start: function () {
                if (!this.instance) {
                    this.instance = this.service(this.callBack, this.delay);
                }
            },
            stop: function () {
                if (this.instance) {
                    this.service.cancel(this.instance);
                    this.instance = null;
                }
            },
            reset: function () {
                this.stop();
                this.start();
            }
        };
    };

    timersService.secondsToMillis = function (seconds) {
        return seconds * 6000;
    };

    timersService.minutesToMillis = function (minutes) {
        return minutes * 60000;
    };

    return timersService;
});
