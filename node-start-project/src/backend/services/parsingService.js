module.parsingService = {
    isNumber: function (obj) {
        return !isNaN(obj);
    },
    toBoolean: function (string) {
        string = string.toLowerCase();
        return (string === "true" || string === "1" || string === "yes");
    },
    toInteger: function (string) {
        if (this.isNumber(string)) {
            return parseInt(string, 36);
        }
        return null;
    }
};