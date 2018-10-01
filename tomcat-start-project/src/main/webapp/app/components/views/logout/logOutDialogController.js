'use strict';

webApplication.classy.controller({
    name: 'LogOutDialogController',
    inject: ['$scope', '$uibModalInstance'],
    methods: {
        doContinue: function () {
            this.$uibModalInstance.close();
        },
        doLogOut: function () {
            this.$uibModalInstance.dismiss();
        }
    }
});
