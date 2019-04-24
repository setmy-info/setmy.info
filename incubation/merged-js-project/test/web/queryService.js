jsdi.service("queryService", function () {

    var logicService = {
    };

    logicService.init = function () {
        console.log("initalizing queryService");
    };

    logicService.get = function (url, successCallback, errorCallback) {
        var request = new XMLHttpRequest();
        request.open('GET', url, true);
        request.onload = function () {
            if (request.status >= 200 && request.status < 400) {
                if (successCallback) {
                    successCallback(JSON.parse(request.responseText));
                }
            } else {
                if (errorCallback) {
                    errorCallback('Error!');
                }
            }
        };
        request.onerror = function () {
            if (errorCallback) {
                errorCallback('Error!');
            }
        };
        request.send();
    };

    return logicService;
});
