(function() {
  'use strict';

  angular
    .module('app.associados')
    .controller('AssociadosController', AssociadosController);

  /** @ngInject */
  function AssociadosController($scope, $mdSidenav, User, Associados, msUtils, $mdDialog, $document, api, $window) {

    var vm = this;

    // Data

    vm.associados = Associados;
    //vm.user = User.data;
    vm.listType = 'all';
    vm.listOrder = 'nome';
    vm.listOrderAsc = false;
    vm.selectedAssociados = [];


    // Methods
    vm.openAssociadoDialog = openAssociadoDialog;
    vm.deleteAssociadoConfirm = deleteAssociadoConfirm;
    vm.deleteAssociado = deleteAssociado;
    vm.deleteSelectedAssociados = deleteSelectedAssociados;
    vm.toggleSelectAssociado = toggleSelectAssociado;
    vm.deselectAssociados = deselectAssociados;
    vm.selectAllAssociados = selectAllAssociados;
    vm.deleteAssociado = deleteAssociado;
    vm.toggleInArray = msUtils.toggleInArray;
    vm.exists = msUtils.exists;
    //////////

    //vm.currentUser = $window.sessionStorage.getItem("currentUser");

    console.log("Logado:" + User.nome);
    //console.log("Logado: " + vm.currentUser.nome);

    /**.
     * Open new Associado dialog
     *
     * @param ev
     * @param contact
     */
    function openAssociadoDialog(ev, associado) {
      $mdDialog.show({
        controller: 'AssociadoDialogController',
        controllerAs: 'vm',
        templateUrl: 'app/main/associados/dialogs/associado/associado-dialog.html',
        parent: angular.element($document.find('#content-container')),
        targetEvent: ev,
        clickOutsideToClose: true,
        locals: {
          Associado: associado,
          User: vm.user,
          Associados: vm.associados
        }
      });
    }

    /**
     * Delete Associado Confirm Dialog
     */
    function deleteAssociadoConfirm(associado, ev) {
      var confirm = $mdDialog.confirm()
        .title('Você tem certeza de que deseja apagar este associado?')
        .htmlContent('<b>' + associado.nome + ' (' + associado.cpf + ')</b>' + ' será apagado(a).')
        .ariaLabel('apagar contato')
        .targetEvent(ev)
        .ok('Sim')
        .cancel('Cancelar');

      $mdDialog.show(confirm).then(function() {
        deleteAssociado(associado);
        vm.selectedAssociados = [];

      }, function() {
        console.log('Cancelou');
      });
    }

    /**
     * Delete Associado
     */
    function deleteAssociado(associado) {

      // TODO Remover também a [formaPgto] do Associado.

      // Remove o Associado do BD
      console.log('deleteAssociado @ associados.controller.js');
      api.associado.getByCpf.delete({
          'cpf': associado.cpf
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

      // Remove a da lista na tela a linha deste Associado
      vm.associados.splice(vm.associados.indexOf(associado), 1);
    }

    /**
     * Delete Selected Associados
     */
    function deleteSelectedAssociados(ev) {
      var confirm = $mdDialog.confirm()
        .title('Você tem certeza de que deseja apagar os associados selecionados?')
        .htmlContent('<b>' + vm.selectedAssociados.length + ' selecionado(s)</b>' + ' será(ão) apagado(s).')
        .ariaLabel('apagar contatos')
        .targetEvent(ev)
        .ok('Sim')
        .cancel('Cancelar');

      $mdDialog.show(confirm).then(function() {

        vm.selectedAssociados.forEach(function(associado) {
          deleteAssociado(associado);
        });

        vm.selectedAssociados = [];

      });

    }

    /**
     * Toggle selected status of the associado
     *
     * @param associado
     * @param event
     */
    function toggleSelectAssociado(associado, event) {
      if (event) {
        event.stopPropagation();
      }

      if (vm.selectedAssociados.indexOf(associado) > -1) {
        vm.selectedAssociados.splice(vm.selectedAssociados.indexOf(associado), 1);
      } else {
        vm.selectedAssociados.push(associado);
      }
    }

    /**
     * Deselect associados
     */
    function deselectAssociados() {
      vm.selectedAssociados = [];
    }

    /**
     * Sselect all associados
     */
    function selectAllAssociados() {
      vm.selectedAssociados = $scope.filteredAssociados;
    }

  }

})();
