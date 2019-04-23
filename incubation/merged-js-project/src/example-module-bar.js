"use strict";

var jsdi = (function (jsdi, global) {

    jsdi.bar = function () {
        return 'Hello World from bar!';
    };

    return jsdi;

})((typeof exports !== 'undefined') ? exports : {}, (typeof window !== 'undefined') ? window : global);
