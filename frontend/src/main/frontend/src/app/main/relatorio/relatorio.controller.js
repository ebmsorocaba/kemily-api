(function ()
{
    'use strict';

    angular
        .module('app.relatorio')
        .controller('RelatorioController', RelatorioController);

    /** @ngInject */
    function RelatorioController($filter ,$scope, $mdSidenav, User, msUtils, $mdDialog, $document, api)
    {
      var vm = this;

      vm.user = User.data;

      // Methods
      vm.limpaForm = limpaForm;
      vm.buscaCpf = buscaCpf;


      vm.openAssociadoDialog = openAssociadoDialog

      vm.toggleInArray = msUtils.toggleInArray;
      vm.exists = msUtils.exists;
      //////////

      /*
      vm.cpf ='333.333.333-33';
      vm.dataInicio = new Date(2015, 0, 1, 0, 0, 0, 0);
      vm.dataFim = new Date(2015, 3, 1, 0, 0, 0, 0);
      */
      console.log(vm.dataInicio);
      console.log(vm.dataFim);


      function limpaForm() {
        console.log('limpaForm @ pagamento.controller.js');
        vm.cpf='';
        vm.dataInicio='';
        vm.dataFim='';
      }

      function buscaCpf() {
        console.log('buscaCpf @ pagamento.controller.js');
        if (vm.cpf) {
          // Busca o Associado no BD para recuperar a data de pagamento e valor esperados
          api.associado.getByCpf.get({
              'cpf': vm.cpf
            },
            // Sucesso
            function(response) {
              console.log(response);
            },
            // Erro
            function(response) {
              console.error(response);
            }
          );
        };


      }




      function openAssociadoDialog(ev) {
        $mdDialog.show({
          controller: 'RelatorioDialogController',
          controllerAs: 'vm',
          templateUrl: 'app/main/relatorio/dialogs/relatorio/relatorio-dialog.html',
          parent: angular.element($document.find('#content-container')),
          targetEvent: ev,
          clickOutsideToClose: true,
          locals: {
            //Cpf: vm.cpf,
            DataInicio: vm.dataInicio,
            DataFim: vm.dataFim
          }

        });
      }




    }
})();
