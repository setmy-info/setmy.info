'use strict';

webAppServices.factory('translationsService', function (Translations, modelService, configurationService, tmhDynamicLocale) {

    var service = {
        init: function () {
            this.loadTranslations(configurationService.getLanguage());
        },
        loadTranslations: function (lang) {
            var translations = Translations.get({lang: lang}, function () {
                modelService.translations.keys = translations;
                modelService.translations.selectedLanguage = modelService.translations.languages[lang];
                tmhDynamicLocale.set(lang);
            });
        },
        changeLanguage: function (lang) {
            this.loadTranslations(lang);
            configurationService.setLanguage(lang);
        }
    };

    return service;
});
