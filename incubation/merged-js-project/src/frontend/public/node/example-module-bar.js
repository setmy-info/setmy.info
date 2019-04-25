"use strict";

(function (global) {

    var jsdi = global.jsdi = global.jsdi || {};

    var exampleModuleBarService = {

        bar: function () {
            return 'Hello World from bar!';
        }
    };

    global.jsdi.exampleModuleBarService = exampleModuleBarService;

})(typeof window === 'undefined' ? global : window);
