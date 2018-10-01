'use strict';

webApplication.classy.controller({
    name: 'ModalDialogController',
    inject: ['$scope', '$uibModalInstance', 'items', 'selected'],
    init: function () {
        this.$scope.items = this.items;
        this.$scope.selected = this.selected;
        this.$scope.currentSelected = this.$scope.selected.item;
    },
    methods: {
        selecting: function ($event, item) {
            $event.preventDefault();
            this.$scope.currentSelected = item;

        },
        ok: function () {
            this.$scope.selected.item = this.$scope.currentSelected;
            this.$uibModalInstance.close(this.$scope.currentSelected);
        },
        cancel: function () {
            this.$uibModalInstance.dismiss('cancel');
        }
    }
});
