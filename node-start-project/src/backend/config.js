const env = require('./services/environmentService');

module.exports = {
    serverPort: env.int("PORT", 3000) || env.str("PORT"),
    log: {
        maxsize: env.int("LOG_SIZE", 25600),
        tailable: env.bool("LOG_TAILABLE", true),
        maxFiles: env.int("LOG_MAX_FILES", 512),
        fileName: env.str("LOG_FILE_NAME", "node-start-project.log"),
        errorFileName: env.str("ERROR_LOG_FILE_NAME", "node-start-project-error.log")
    },
    environments: {
        DEV: "development",
        LIVE: "production"
    }
};
