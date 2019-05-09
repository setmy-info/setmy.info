const parsingService = require('./parsingService');

module.exports = {
    env: process.env,
    bool: function (propertyName, defaultVal) {
        var value = this.str(propertyName, null);
        if (!value) {
            return defaultVal;
        }
        return parsingService.toBoolean(value.toLowerCase());
    },
    int: function (propertyName, defaultVal) {
        var value = this.str(propertyName, null);
        if (!value) {
            return defaultVal;
        }
        return parsingService.toInteger(value);
    },
    str: function (propertyName, defaultVal) {
        var value = this.e(propertyName);
        if (!value) {
            return defaultVal;
        }
        return this.e(propertyName);
    },
    e: function (propertyName) {
        return this.env[propertyName];
    }
};
