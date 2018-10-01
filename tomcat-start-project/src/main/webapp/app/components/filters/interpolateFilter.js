'use strict';

webAppFilters.filter('interpolate', ['VERSION', function (version) {
        /*
         http://stackoverflow.com/questions/7975005/format-a-string-using-placeholders-and-an-object-of-substitutions
         http://www.codeproject.com/Tips/201899/String-Format-in-JavaScript
         http://stackoverflow.com/questions/1038746/equivalent-of-string-format-in-jquery
         */
        return function (text) {
            //var text = arguments[0];
            return String(text).replace(/\%VERSION\%/mg, version);
        };
    }]);
