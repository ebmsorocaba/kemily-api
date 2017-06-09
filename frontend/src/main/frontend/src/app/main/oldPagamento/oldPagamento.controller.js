(function() {
  'use strict';

  angular
    .module('app.oldPagamento')
    .controller('OldPagamentoController', OldPagamentoController)


  /** @ngInject */
  function OldPagamentoController($scope, $mdSidenav, User, msUtils, $mdDialog, $document, api, $state) {

    var vm = this;

    // Data
    // vm.associados = Associados;
    vm.associado = null;
    vm.user = User.data;
    // vm.filterIds = null;
    // vm.listType = 'all';
    // vm.listOrder = 'nome';
    // vm.listOrderAsc = false;
    // vm.selectedContacts = [];
    // vm.newGroupName = '';

    // Pagamento
    vm.pagamento = {
      'valorPago': null,
      'vencimento': new Date(),
      'dataPgto': new Date(),
      'formaPgto': {
        'associado': {
          'cpf': '',
        },
        'formaPagamento': "Dinheiro",
      },
    };

    // Methods
    vm.limpaForm = limpaForm;
    vm.buscaCpf = buscaCpf;
    vm.cadastrarPagamento = cadastrarPagamento;

    vm.toggleInArray = msUtils.toggleInArray;
    vm.exists = msUtils.exists;
    //////////

    vm.sucess = sucess;
    vm.fail = fail;


    function limpaForm() {
      console.log('limpaForm @ pagamento.controller.js');
      vm.pagamento.formaPgto.associado.cpf = "";
      vm.pagamento.valorPago = null;
      vm.pagamento.dataPgto = new Date();
      vm.associado = null;
    }

    function buscaCpf() {
      console.log('buscaCpf @ pagamento.controller.js');
      if (vm.pagamento.formaPgto.associado.cpf) {
        // Busca o Associado no BD para recuperar a data de pagamento e valor esperados
        api.associado.getByCpf.get({
            'cpf': vm.pagamento.formaPgto.associado.cpf
          },
          // Sucesso
          function(response) {
            console.log(response);
            vm.associado = response;

            // Define o valor esperado
            vm.pagamento.valorPago = vm.associado.valorAtual;

            // Define a data de pagamento esperada
            vm.pagamento.vencimento.setDate(vm.associado.vencAtual);
          },
          // Erro
          function(response) {
            console.error(response);
          }
        );
      };
    }

    function cadastrarPagamento(ev) {
      console.log('cadastrarPagamento @ pagamento.controller.js');
      // Temporário


      // Grava o pagamento no BD
      api.pagamento.list.save(vm.pagamento,
        // Exibe o resultado no console do navegador:
        // Sucesso
        function(response) {
          console.log(response);
          vm.sucess(ev);
        },
        // Erro
        function(response) {
          console.error(response);
          vm.fail(ev);
        });
    };

    function sucess(ev) {
    // Appending dialog to document.body to cover sidenav in docs app
      var confirm = $mdDialog.alert()
            .title('SUCESSO')
            .textContent('O pagamento foi cadastrado com sucesso')
            .ariaLabel('Tudo ok!')
            .targetEvent(ev)
            .ok('OK!')

      $mdDialog.show(confirm).then(function() {
        $scope.status = 'OK!';
        $state.reload();
      });
  };

  function fail(ev) {
  // Appending dialog to document.body to cover sidenav in docs app
    var confirm = $mdDialog.alert()
          .title('FALHA')
          .textContent('Houve algum problema e pagamento não foi registrado, verifique os campos ou contate um administrador')
          .ariaLabel('Vou verificar!')
          .targetEvent(ev)
          .ok('Vou verificar!')

    $mdDialog.show(confirm).then(function() {
      $scope.status = 'falha!';

    });
  };

  }

})();
