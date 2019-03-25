"use strict";

var jsdi = require('../../../main/webapp/js/servicejs');

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

console.log(jsdi.get().serviceA.getText());