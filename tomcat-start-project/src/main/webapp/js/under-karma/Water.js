var Water = (function () {

    function Water() {
        this.forms = [];
    }

    Water.waterForms = ["ice", "water", "vapor"];

    Water.instance = null;

    Water.getInstance = function () {
        if (!Water.instance) {
            Water.instance = new Water();
            //Water.instance.forms = [];// Bug solved!
        }
        Water.instance.forms = [];// Bug solved!
        Water.instance.initWater();
        return Water.instance;
    };

    Water.prototype.initWater = function () {
        for (var i = 0; i < Water.waterForms.length; i++) {
            this.forms.push(Water.waterForms[i]);
        }
    };

    return Water;
})();
