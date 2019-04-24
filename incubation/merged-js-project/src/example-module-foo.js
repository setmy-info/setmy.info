"use strict";

var jsdi = (function (jsdi, global) {

    global.globalVariable = "Global Hello World too!";

    jsdi.exampleModuleFooService = {
        foo: function () {
            return 'Hello World from foo!';
        }
    };

    return jsdi;

})((typeof exports !== 'undefined') ? exports : jsdi || {}, (typeof window !== 'undefined') ? window : global);
