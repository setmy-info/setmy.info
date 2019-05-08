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
        new winston.transports.File({filename: "error.log", level: "error", maxsize: 1024, tailable: true, maxFiles: 100}),
        new winston.transports.File({filename: "node.log", maxsize: 1024, tailable: true, maxFiles: 1000})
    ]
});

if (process.env.NODE_ENV !== "production") {
    logger.add(new winston.transports.Console({
        format: winston.format.simple()
    }));
}

module.exports = logger;
