'use strict';

webAppControllers.classy.controller({
    name: 'LanguageSelectorDirectiveController',
    inject: ['$scope', '$rootScope', '$log', 'translationsService', 'configurationService', '$route'],
    data: {
        counter: 0
    },
    init: function () {
        this.validate();
        this.translationsService.init();
    },
    methods: {
        validate: function () {
            if (this.data.counter > 0) {
                throw "Too much language directives used! Only one directive can be used in application!";
            }
            this.data.counter++;
        },
        languageChanged: function (lang) {
            this.translationsService.changeLanguage(lang);
        }
    }
});

webAppDirectives.directive('languageSelector', [function () {
        return {
            restrict: 'A',
            templateUrl: directivesDirectory + '/languageSelectorDirective.html',
            controller: 'LanguageSelectorDirectiveController'
        };
    }]);
