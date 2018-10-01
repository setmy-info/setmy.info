'use strict';

webAppServices.factory('modelService', function (configurationService, $parse) {

    var service = {
        DOTREGEXP: /\./g,
        $parse: $parse,
        variables: {},
        isExistingVariable: function (variableName) {
            var getter = this.$parse(variableName);
            return !!getter(this);
        },
        get: function (variableName) {
            var variable = this.getVariable(variableName);
            return variable.get();
        },
        set: function (variableName, value) {
            var variable = this.getVariable(variableName);
            variable.set(value);
        },
        getVariable: function (variableName) {
            var holderName = variableName.replace(this.DOTREGEXP, "$"), //TODO : investigate, is . supported in variable name.
                    variable = this.variables[holderName], getter, setter;
            if (variable) {
                return variable;
            }
            getter = this.$parse(variableName);
            setter = getter.assign;
            variable = this.variables[holderName] = {
                model: this,
                getter: getter,
                setter: setter,
                get: function () {
                    return this.getter(this.model);
                },
                set: function (value) {
                    this.setter(this.model, value);
                }
            };
            return variable;
        },
        session: {
            user: null,
            minutesLeft: configurationService.config.defaultSessionTimeoutMinutes - webApplication.minutesIdle
        },
        translations: {
            languages: {
                et: {lang: 'et', text: 'Eesti - Estonian'},
                en: {lang: 'en', text: 'English'}
            }
        },
        LanguageSelectorDirectiveController: {
        },
        LoginController: {
        },
        View1Controller: {
            exampleText: "Example Text 1!!!"
        },
        View2Controller: {
            exampleText: "Example Text 2!!!"
        }
    };

    return service;
});
