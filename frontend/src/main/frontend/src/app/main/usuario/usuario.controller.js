(function ()
{
    'use strict';

    angular
        .module('app.usuario')
        .controller('UsuarioController', UsuarioController);

    /** @ngInject */
    function UsuarioController($scope)
    {
        var vm = this;

        // Data
        // vm.helloText = SampleData.data.helloText;

        // Dados de exemplo para testes
        $scope.usuario = "usutal";
        $scope.senha = "@12345";
        $scope.grupo = "Administradores";

        // Methods
        vm.limpaForm = limpaForm;
        //////////

        function limpaForm(){
          $scope.usuario = "";
          $scope.senha = "";
          $scope.grupo = "";
    }
  }
})();
