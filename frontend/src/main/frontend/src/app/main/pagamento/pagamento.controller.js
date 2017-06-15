(function() {
  'use strict';

  angular
    .module('app.pagamento')
    .controller('PagamentoController', PagamentoController);

  /** @ngInject */
  function PagamentoController($scope, $mdSidenav, Associados, Pagamentos, User, msUtils, $mdDialog, $document, api, $filter) {

    var vm = this;

    // Data

    vm.pagamentos = Pagamentos;
    vm.associados = Associados;
    vm.user = User.data;
    vm.listType = 'all';
    vm.listOrder = 'nome';
    vm.listOrderAsc = true;
    vm.selectedPagamentos = [];


    // Methods
    vm.openPagamentoDialog = openPagamentoDialog;
    vm.deletePagamentoConfirm = deletePagamentoConfirm;
    vm.deletePagamento = deletePagamento;
    vm.deleteSelectedPagamentos = deleteSelectedPagamentos;
    vm.toggleSelectPagamento = toggleSelectPagamento;
    vm.deselectPagamentos = deselectPagamentos;
    vm.selectAllPagamentos = selectAllPagamentos;
    vm.deletePagamento = deletePagamento;
    vm.toggleInArray = msUtils.toggleInArray;
    vm.exists = msUtils.exists;
    //////////

    /**
     * Open new Pagamento dialog
     *
     * @param ev
     * @param contact
     */
    function openPagamentoDialog(ev, pagamento) {
      $mdDialog.show({
        controller: 'PagamentoDialogController',
        controllerAs: 'vm',
        templateUrl: 'app/main/pagamento/dialogs/pagamento/pagamento-dialog.html',
        parent: (angular.element(document.body)),
        targetEvent: ev,
        clickOutsideToClose: true,
        locals: {
          Pagamento: pagamento,
          User: vm.user,
          Pagamentos: vm.pagamentos,
          Associados: vm.associados
        }
      });
    }

    /**
     * Delete Pagamento Confirm Dialog
     */
    function deletePagamentoConfirm(pagamento, ev) {
      var confirm = $mdDialog.confirm()
        .title('Você tem certeza de que deseja apagar este pagamento?')
        .htmlContent('<b>' + 'Nº pagamento: ' + '</b>' + pagamento.id + '</br>' + '<b>' + 'Valor: ' + '</b>' + ($filter('currency')(pagamento.valorPago, 'R$ '))+ '</br>' + ' será apagado.')
        .ariaLabel('apagar contato')
        .targetEvent(ev)
        .ok('Sim')
        .cancel('Cancelar');

      $mdDialog.show(confirm).then(function() {
        deletePagamento(pagamento);
        vm.selectedPagamentos = [];

      }, function() {
        console.log('Cancelou');
      });
    }

    /**
     * Delete Pagamento
     */
    function deletePagamento(pagamento) {

      // TODO Remover também a [formaPgto] do Pagamento.

      // Remove o Pagamento do BD
      console.log('deletePagamento @ pagamentos.controller.js');
      api.pagamento.getById.delete({
          'id': pagamento.id
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

      // Remove a da lista na tela a linha deste Pagamento
      vm.pagamentos.splice(vm.pagamentos.indexOf(pagamento), 1);
    }

    /**
     * Delete Selected Pagamentos
     */
    function deleteSelectedPagamentos(ev) {
      var confirm = $mdDialog.confirm()
        .title('Você tem certeza de que deseja apagar os pagamentos selecionados?')
        .htmlContent('<b>' + vm.selectedPagamentos.length + ' selecionado(s)</b>' + ' será(ão) apagado(s).')
        .ariaLabel('apagar contatos')
        .targetEvent(ev)
        .ok('Sim')
        .cancel('Cancelar');

      $mdDialog.show(confirm).then(function() {

        vm.selectedPagamentos.forEach(function(pagamento) {
          deletePagamento(pagamento);
        });

        vm.selectedPagamentos = [];

      });

    }

    /**
     * Toggle selected status of the pagamento
     *
     * @param pagamento
     * @param event
     */
    function toggleSelectPagamento(pagamento, event) {
      if (event) {
        event.stopPropagation();
      }

      if (vm.selectedPagamentos.indexOf(pagamento) > -1) {
        vm.selectedPagamentos.splice(vm.selectedPagamentos.indexOf(pagamento), 1);
      } else {
        vm.selectedPagamentos.push(pagamento);
      }
    }

    /**
     * Deselect pagamentos
     */
    function deselectPagamentos() {
      vm.selectedPagamentos = [];
    }

    /**
     * Sselect all pagamentos
     */
    function selectAllPagamentos() {
      vm.selectedPagamentos = $scope.filteredPagamentos;
    }

  }

})();
