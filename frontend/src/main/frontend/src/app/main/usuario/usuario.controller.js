(function() {
  'use strict';

  angular
    .module('app.usuario')
    .controller('UsuariosController', UsuariosController);

  /** @ngInject */
  function UsuariosController($scope, $mdSidenav, Usuarios, User, msUtils, $mdDialog, $document, api) {

    var vm = this;

    // Data
    // vm.formaPgto = FormaPgto;

    vm.contacts = Usuarios;
    vm.user = User.data;
    // vm.filterIds = null;
    vm.listType = 'all';
    vm.listOrder = 'nome';
    vm.listOrderAsc = false;
    vm.selectedContacts = [];
    // vm.newGroupName = '';

    // Methods
    // vm.filterChange = filterChange;
    vm.openContactDialog = openContactDialog;
    vm.deleteContactConfirm = deleteContactConfirm;
    vm.deleteContact = deleteContact;
    vm.deleteSelectedContacts = deleteSelectedContacts;
    vm.toggleSelectContact = toggleSelectContact;
    vm.deselectContacts = deselectContacts;
    vm.selectAllContacts = selectAllContacts;
    vm.deleteContact = deleteContact;
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
    function openContactDialog(ev, contact) {
      $mdDialog.show({
        controller: 'UsuarioDialogController',
        controllerAs: 'vm',
        templateUrl: 'app/main/usuario/dialogs/contact/usuario-dialog.html',
        parent: (angular.element(document.body)),
        targetEvent: ev,
        clickOutsideToClose: true,
        locals: {
          Contact: contact,
          User: vm.user,
          Contacts: vm.contacts
        }
      });
    }

    /**
     * Delete Contact Confirm Dialog
     */
    function deleteContactConfirm(contact, ev) {
      var confirm = $mdDialog.confirm()
        .title('Você tem certeza de que deseja apagar este usuário?')
        .htmlContent('<b>' + contact.nome + ' (' + contact.nome + ')</b>' + ' será apagado(a).')
        .ariaLabel('apagar usuario')
        .targetEvent(ev)
        .ok('Sim')
        .cancel('Cancelar');

      $mdDialog.show(confirm).then(function() {
        deleteContact(contact);
        vm.selectedContacts = [];

      }, function() {
        console.log('Cancelou');
      });
    }

    /**
     * Delete Contact
     */
    function deleteContact(contact) {

      // TODO Remover também a [formaPgto] do Associado.

      // Remove o Associado do BD
      console.log('deleteContact @ contacts.controller.js');
        api.usuario.getByNome.delete({
          'nome': contact.nome
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
      vm.contacts.splice(vm.contacts.indexOf(contact), 1);
    }

    /**
     * Delete Selected Contacts
     */
    function deleteSelectedContacts(ev) {
      var confirm = $mdDialog.confirm()
        .title('Você tem certeza de que deseja apagar os usuários selecionados?')
        .htmlContent('<b>' + vm.selectedContacts.length + ' selecionado(s)</b>' + ' será(ão) apagado(s).')
        .ariaLabel('apagar contatos')
        .targetEvent(ev)
        .ok('Sim')
        .cancel('Cancelar');

      $mdDialog.show(confirm).then(function() {

        vm.selectedContacts.forEach(function(contact) {
          deleteContact(contact);
        });

        vm.selectedContacts = [];

      });

    }

    /**
     * Toggle selected status of the contact
     *
     * @param contact
     * @param event
     */
    function toggleSelectContact(contact, event) {
      if (event) {
        event.stopPropagation();
      }

      if (vm.selectedContacts.indexOf(contact) > -1) {
        vm.selectedContacts.splice(vm.selectedContacts.indexOf(contact), 1);
      } else {
        vm.selectedContacts.push(contact);
      }
    }

    /**
     * Deselect contacts
     */
    function deselectContacts() {
      vm.selectedContacts = [];
    }

    /**
     * Sselect all contacts
     */
    function selectAllContacts() {
      vm.selectedContacts = $scope.filteredContacts;
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
