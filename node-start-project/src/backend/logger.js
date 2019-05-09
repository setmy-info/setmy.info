const config = require('./config');
const env = require('./services/environmentService').env;
const winston = require('winston');
const {createLogger, format, transports} = winston;
const {combine, timestamp, label, printf} = format;

const customFormat = printf(({ level, message, label, timestamp }) => {
    return `${timestamp} [${label}] ${level}: ${message}`;
});

const logger = winston.createLogger({
    level: "debug",
    format: combine(label({}), timestamp(), customFormat),
    transports: [
        new winston.transports.File({filename: config.log.fileName, maxsize: config.log.maxsize, tailable: config.log.tailable, maxFiles: config.log.maxFiles}),
        new winston.transports.File({filename: config.log.errorFileName, level: "error", maxsize: config.log.maxsize, tailable: config.log.tailable, maxFiles: config.log.maxFiles})
    ]
});

if (notInLive()) {
    logger.add(new winston.transports.Console({
        format: winston.format.simple()
    }));
}

function notInLive() {
    return env.NODE_ENV !== config.environments.LIVE;
}

module.exports = logger;
