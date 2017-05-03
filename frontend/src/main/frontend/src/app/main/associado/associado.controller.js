(function ()
{
    'use strict';

    angular
        .module('app.associado')
        .controller('AssociadoController', AssociadoController);

    /** @ngInject */
    function AssociadoController($scope)
    {
        var vm = this;

        // Data
        //vm.helloText = SampleData.data.helloText;

        // Dados de exemplo para testes
        $scope.nome = "Fulano da Costa";
        $scope.cpf = "12345678911";
        $scope.email = "fulano.costa@empresa.com.br";
        $scope.celular = "159977990011";
        $scope.formaPagamento = 2;

        // Methods
        vm.limpaForm = limpaForm;
        //////////

        function limpaForm(){
          $scope.nome = "";
          $scope.cpf = "";
          $scope.email = "";
          $scope.celular = "";
          $scope.formaPagamento = 0;
        }
    }
})();
