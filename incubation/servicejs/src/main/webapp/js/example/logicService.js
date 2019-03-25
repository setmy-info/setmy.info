jsdi.service("logicService", function () {

    /**
     * This like code is usually in controllers.
     *
     * jsdi.get().logicService.getPerson();
     *
     * jsdi.get().personsService;
     *
     * jsdi.get().personsService.person
     *
     * 1. JS console.
     * 2. JS debug.
     * 3. Data check.
     * 
     * */
    var logicService = {
        personsService: null
    };

    logicService.init = function () {
        console.log("initalizing logicService");
    };

    logicService.getPerson = function () {
        this.personsService.loadPerson(function (person) {//Currenlty with callback. Can be rewriten to promises.
            document.getElementById('person').innerHTML = person.name + " as " + person.role;
        });
    };

    return logicService;
});
