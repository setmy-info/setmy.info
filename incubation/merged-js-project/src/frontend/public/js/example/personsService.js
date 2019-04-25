"use strict";

jsdi.service("personsService", function () {

    var personsService = {
        rolesService: null,
        queryService: null
    };

    personsService.init = function () {
        console.log("initalizing personsService");
    };

    personsService.person = null;

    personsService.URL = "json/person.json";

    personsService.loadPerson = function (callback) {
        var that = this;
        if (this.person) {
            callback(this.person);
            return;
        }
        this.queryService.get(this.URL, function (person) {
            that.person = person;
            that.rolesService.getPersonRole(that.person.name, function (role) {
                that.person.role = role;
                callback(personsService.person);
            });
        });
    };

    return personsService;
});
