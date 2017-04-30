(function ()
{
    'use strict';

    angular
        .module('app.associado')
        .controller('AssociadoController', AssociadoController);

    /** @ngInject */
    function AssociadoController(SampleData)
    {
        var vm = this;

        // Data
        vm.helloText = SampleData.data.helloText;

        // Methods

        //////////
    }
})();
