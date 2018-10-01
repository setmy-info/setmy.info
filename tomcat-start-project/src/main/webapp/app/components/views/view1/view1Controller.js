'use strict';

webAppViews.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/view1', {
            templateUrl: 'components/views/view1/view1.html',
            controller: 'View1Controller'
        });
    }]);

webApplication.classy.controller({
    name: 'View1Controller',
    inject: ['$scope', 'modalDialogService', '$log'],
    init: function () {
        this.$scope.dialogItems = [{firstName: 'Imre'}, {firstName: 'Jaana'}];
        this.$scope.dialogSelected = {item: this.$scope.dialogItems[0]};
        this.$scope.inlineOptions = {
            minDate: new Date(),
            showWeeks: true
        };
    },
    methods: {
        openDialog: function () {
            var that = this;
            this.$scope.modalInstance = this.modalDialogService.dialog('/dialog/modalDialog.html', 'ModalDialogController', {
                items: function () {
                    return that.$scope.dialogItems;
                },
                selected: function () {
                    return that.$scope.dialogSelected;
                }
            });
            this.$scope.modalInstance.result.then(function (selectedItem) {
                that.$log.info('Modal accepted : ', selectedItem);
                that.$scope.selected = selectedItem;
            }, function (reason) {
                that.$log.info('Modal dismissed with reason: ', reason);
            });
        }
    }
});
