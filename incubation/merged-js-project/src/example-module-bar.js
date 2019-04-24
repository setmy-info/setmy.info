"use strict";

var jsdi = (function (jsdi, global) {

    jsdi.bar = function () {
        return 'Hello World from bar!';
    };

    return jsdi;

})((typeof exports !== 'undefined') ? exports : jsdi || {}, (typeof window !== 'undefined') ? window : global);
