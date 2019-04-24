jsdi.service("serviceB", function () {

    var serviceB = {
        inject: ['serviceC']
    };

    serviceB.init = function () {
        console.log("initalizing serviceB");
    };

    serviceB.getName = function () {
        return "serviceB" + " " + this.serviceC.getName();
    };

    return serviceB;
});
