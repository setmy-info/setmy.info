exports.config = {
    baseUrl: 'http://localhost:7777/app/#/login',
    seleniumAddress: 'http://localhost:4444/wd/hub',
    specs: [
        '*Spec.js'
    ],
    multiCapabilities: [{
            browserName: 'firefox',
            version: '',
            platform: 'ANY'
        }, {
            browserName: 'internet explorer',
            version: '',
            platform: '11'
        }/*, {
         browserName: 'chrome',
         version: '',
         platform: 'ANY'
         }, {
         browserName: 'phantomjs',
         version: '',
         platform: 'ANY'
         }*/],
    rootElement: 'body',
    allScriptsTimeout: 11000,
    onPrepare: function () {
    },
    jasmineNodeOpts: {
        onComplete: function () {},
        isVerbose: true,
        showColors: true,
        includeStackTrace: true,
        defaultTimeoutInterval: 30000
    }
};
