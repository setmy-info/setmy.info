"use strict";

(function (global) {

    global.jsdi = global.jsdi || {};

    var exampleModuleFooService = {

        foo: function () {
            return 'Hello World from foo!';
        }

    };

    global.jsdi.exampleModuleFooService = exampleModuleFooService;

})(typeof window === 'undefined' ? global : window);
