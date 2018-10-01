(function () {
    var lang = JSON.parse(localStorage.getItem('lang')) || 'et';
    document.write('<script src="lib/angular-i18n/angular-locale_' + lang + '.js"><\/script>');
})();
