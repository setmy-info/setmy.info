'use strict';

webAppServices.factory('schedulerService', function ($log, timersService, Ping) {

    var schedulerService = {
        counter: 0
    };

    schedulerService.init = function () {
        this.newSecondInterval();
        //this.interval.start();
    };

    schedulerService.newSecondInterval = function () {
        var that = this;
        this.interval = timersService.newInterval(function () {
            that.exec();
        }, timersService.SECOND);
    };

    schedulerService.exec = function () {
        $log.debug('exec');
        if (this.counter === 15) {
            this.ping();
            this.counter = 0;
        } else {
            this.counter++;
        }
    };

    schedulerService.ping = function () {
        //TODO : ping only when activity is changed
        $log.debug('Ping');
        Ping.get(null, function (data) {
            $log.debug('Ping data', data);
        });
    };

    return schedulerService;
});
