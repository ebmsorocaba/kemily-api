(function() {
  'use strict';

  angular
    .module('app.associados')
    .controller('AssociadosController', AssociadosController);

  /** @ngInject */
  function AssociadosController($scope, $mdSidenav, Associados, User, msUtils, $mdDialog, $document, api) {

    var vm = this;

    // Data
    // vm.formaPgto = FormaPgto;

    vm.associados = Associados;
    vm.user = User.data;
    // vm.filterIds = null;
    vm.listType = 'all';
    vm.listOrder = 'nome';
    vm.listOrderAsc = false;
    vm.selectedAssociados = [];
    // vm.newGroupName = '';

    // Methods
    // vm.filterChange = filterChange;
    vm.openAssociadoDialog = openAssociadoDialog;
    vm.deleteAssociadoConfirm = deleteAssociadoConfirm;
    vm.deleteAssociado = deleteAssociado;
    vm.deleteSelectedAssociados = deleteSelectedAssociados;
    vm.toggleSelectAssociado = toggleSelectAssociado;
    vm.deselectAssociados = deselectAssociados;
    vm.selectAllAssociados = selectAllAssociados;
    vm.deleteAssociado = deleteAssociado;
    // vm.addNewGroup = addNewGroup;
    // vm.deleteGroup = deleteGroup;
    // vm.toggleSidenav = toggleSidenav;
    vm.toggleInArray = msUtils.toggleInArray;
    vm.exists = msUtils.exists;
    //////////

    /**
     * Change Contacts List Filter
     * @param type
     */
    // function filterChange(type)
    // {
    //
    //     vm.listType = type;
    //
    //     if ( type === 'all' )
    //     {
    //         vm.filterIds = null;
    //     }
    //     else if ( type === 'frequent' )
    //     {
    //         vm.filterIds = vm.user.frequentContacts;
    //     }
    //     else if ( type === 'starred' )
    //     {
    //         vm.filterIds = vm.user.starred;
    //     }
    //     else if ( angular.isObject(type) )
    //     {
    //         vm.filterIds = type.contactIds;
    //     }
    //
    //     vm.selectedContacts = [];
    //
    // }

    /**
     * Open new contact dialog
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
     * Delete Contact Confirm Dialog
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
     * Delete Contact
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
     * Delete Selected Contacts
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
     * Toggle selected status of the contact
     *
     * @param contact
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
     * Deselect contacts
     */
    function deselectAssociados() {
      vm.selectedAssociados = [];
    }

    /**
     * Sselect all contacts
     */
    function selectAllAssociados() {
      vm.selectedAssociados = $scope.filteredAssociados;
    }

    /**
     *
     */
    // function addNewGroup()
    // {
    //     if ( vm.newGroupName === '' )
    //     {
    //         return;
    //     }
    //
    //     var newGroup = {
    //         'id'        : msUtils.guidGenerator(),
    //         'name'      : vm.newGroupName,
    //         'contactIds': []
    //     };
    //
    //     vm.user.groups.push(newGroup);
    //     vm.newGroupName = '';
    // }

    /**
     * Delete Group
     */
    // function deleteGroup(ev)
    // {
    //     var group = vm.listType;
    //
    //     var confirm = $mdDialog.confirm()
    //         .title('Are you sure want to delete the group?')
    //         .htmlContent('<b>' + group.name + '</b>' + ' will be deleted.')
    //         .ariaLabel('delete group')
    //         .targetEvent(ev)
    //         .ok('OK')
    //         .cancel('CANCEL');
    //
    //     $mdDialog.show(confirm).then(function ()
    //     {
    //
    //         vm.user.groups.splice(vm.user.groups.indexOf(group), 1);
    //
    //         filterChange('all');
    //     });
    //
    // }

    /**
     * Toggle sidenav
     *
     * @param sidenavId
     */
    // function toggleSidenav(sidenavId)
    // {
    //     $mdSidenav(sidenavId).toggle();
    // }

  }

})();
