
webApplication.provider('Aspect', function () {
    return {
        $get: function () {
            var before, after, exception;
            before = function (args) {
                var throwData = (args.exception) ? ' and threw: ' + args.exception.message : '';
                console.log('Method: ' + args.method + ', Pointcut: ' + args.when + ', with arguments: ' +
                        angular.toJson(args.args) + throwData + ' and resolve data: ' +
                        angular.toJson(args.resolveArgs) + ', reject data: ' + angular.toJson(args.rejectArgs) + ', result: ' + angular.toJson(args.result));
            };
            after = function (args) {
                var throwData = (args.exception) ? ' and threw: ' + args.exception.message : '';
                console.log('Method: ' + args.method + ', Pointcut: ' + args.when + ', with arguments: ' +
                        angular.toJson(args.args) + throwData + ' and resolve data: ' +
                        angular.toJson(args.resolveArgs) + ', reject data: ' + angular.toJson(args.rejectArgs) + ', result: ' + angular.toJson(args.result));
            };
            exception = function (args) {
                console.log('%cException: ' + args.exception.message + '. ' + args.method + ' called before proper authorization.',
                        'color: red; text-weight: bold; font-size: 1.2em;');
            };
            return function (args) {
                if (!args.result) {
                    return before(args);
                } else {
                    return after(args);
                }
                if (args.exception) {
                    return exception(args);

                }
            };
        }
    };
});

webApplication.config(function ($provide, executeProvider) {
    executeProvider.annotate($provide, {
        'modelService': [{
                jointPoint: 'around',
                advice: 'Aspect',
                methodPattern: /getHelloWorld/
            }]
    });
});
