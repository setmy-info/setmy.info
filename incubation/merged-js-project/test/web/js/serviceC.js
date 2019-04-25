jsdi.service("serviceC", function () {

    var serviceC = {
    };

    serviceC.init = function () {
        console.log("initalizing serviceC");
    };

    serviceC.getName = function () {
        return "serviceC";
    };

    return serviceC;
});
