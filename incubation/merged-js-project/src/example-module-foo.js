"use strict";

var glob = (function (global) {
    global.jsdi = !!global.jsdi ? global.jsdi : {};
    return global;
})(typeof window !== 'undefined' ? window : global);

var jsdi = (function (jsdi, global) {

    global.globalVariable = "Global Hello World too!";

    global.jsdi.exampleModuleFooService = {
        foo: function () {
            return 'Hello World from foo!';
        }
    };

    return jsdi;

})((typeof exports !== 'undefined') ? exports : {}, ((typeof window !== 'undefined') ? window : global));

