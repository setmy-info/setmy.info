'use strict';

webAppFilters.filter('placeholders', function () {
    return function (text) {
        if (text) {
            var args = Array.prototype.slice.call(arguments);
            args.splice(0, 1);
            return text.format.apply(text, args);
        }
    };
});
