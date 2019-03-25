jsdi.service("rolesService", function () {

    var rolesService = {
        queryService: null
    };

    rolesService.init = function () {
        console.log("initalizing rolesService");
    };

    rolesService.URL = "json/role.json";

    rolesService.getPersonRole = function (name, callback) {
        rolesService.queryService.get(rolesService.URL, function (db) {
            callback(db[name]);
        });
    };

    return rolesService;
});
