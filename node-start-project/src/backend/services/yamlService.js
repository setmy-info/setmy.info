const yaml = require('js-yaml');
const fs = require('fs');
const logger = require("../logger");

const yamlService = {
    load: function (fileName) {
        try {
            var doc = yaml.safeLoad(fs.readFileSync(fileName, 'utf8'));
            return doc;
        } catch (e) {
            logger.error("Loading " + fileName + " yaml error", e);
        }
    }
};

module.exports = yamlService;