var SuperService = (function () {

    function SuperService() {
        this.forms = [];
    }

    // Our super protocol
    SuperService.prototype.talk = function () {
        var result = null,
                spoken = null,
                heard = null;
        spoken = this.speak("This is Ground Control to Major Tom");
        heard = this.listen();
        result = spoken + "! " + heard + "!";
        return result;
    };

    SuperService.prototype.speak = function (textToSpeak) {
        return textToSpeak;
    };

    SuperService.prototype.listen = function () {
        return "This is Major Tom to Ground Control";
    };

    return SuperService;
})();
