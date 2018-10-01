/*
 * su -c 'npm install -g karma karma-chrome-launcher karma-firefox-launcher karma-phantomjs-launcher karma-jasmine karma-junit-reporter karma-coverage karma-html-reporter'
 * 
 */
module.exports = function (config) {
    config.set({
        basePath: './',
        files: [
            // main
            'src/main/webapp/app/js/typescript.js',
            'src/main/webapp/app/lib/html5-boilerplate/dist/js/vendor/modernizr-2.8.3.min.js',
            'src/main/webapp/app/lib/angular/angular.js',
            'src/main/webapp/app/lib/angular-route/angular-route.js',
            'src/main/webapp/app/lib/angular-cookies/angular-cookies.js',
            'src/main/webapp/app/lib/angular-loader/angular-loader.js',
            'src/main/webapp/app/lib/angular-resource/angular-resource.js',
            'src/main/webapp/app/lib/angular-classy/angular-classy.min.js',
            'src/main/webapp/app/lib/angular-aop/build/angular-aop.min.js',
            'src/main/webapp/app/lib/angular-bootstrap/ui-bootstrap.min.js',
            'src/main/webapp/app/lib/angular-mocks/angular-mocks.js',
            // app
            'src/main/webapp/app/js/classyModelPlugin.js',
            'src/main/webapp/app/js/application.js',
            'src/main/webapp/app/components/aop/**/*.js',
            'src/main/webapp/app/components/views/**/*.js',
            'src/main/webapp/app/components/directives/*.js',
            'src/main/webapp/app/components/resources/*.js',
            'src/main/webapp/app/components/filters/*.js',
            'src/main/webapp/app/components/services/*.js',
            'src/main/webapp/app/components/values/*.js',
            // test
            'src/test/js/unit/**/*.js'
        ],
        preprocessors: {
            'src/main/webapp/app/components/**/*.js': ['coverage']
        },
        autoWatch: true,
        colors: true,
        logLevel: config.LOG_INFO,
        port: 9876,
        singleRun: false,
        reporters: ['progress', 'coverage', 'html'],
        frameworks: ['jasmine'],
        browsers: [/*'Chrome',*/'PhantomJS'],
        plugins: [
            'karma-chrome-launcher',
            'karma-firefox-launcher',
            'karma-phantomjs-launcher',
            'karma-jasmine',
            'karma-html-reporter',
            'karma-coverage'
        ],
        htmlReporter: {
            outputDir: 'target/html'
        },
        coverageReporter: {
            type: 'html',
            dir: 'target/coverage'
        }
    });
};
