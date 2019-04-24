jsdi.service("serviceA", function () {

    var serviceA = {
        serviceB: null
    };

    serviceA.init = function () {
        console.log("initalizing serviceA");
    };

    serviceA.getElement = function (id) {
        return document.getElementById(id);
    };

    serviceA.getName = function () {
        return "serviceA";
    };

    serviceA.getText = function () {
        return "From services: " + this.getName() + " " + this.serviceB.getName();
    };

    return serviceA;
});
