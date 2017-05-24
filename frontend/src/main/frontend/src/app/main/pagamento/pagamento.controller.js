(function() {
  'use strict';

  angular
    .module('app.pagamento')
    .controller('PagamentoController', PagamentoController)
    

  /** @ngInject */
  function PagamentoController($scope, $mdSidenav, User, msUtils, $mdDialog, $document, api) {

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

    function cadastrarPagamento() {
      console.log('cadastrarPagamento @ pagamento.controller.js');
      // Temporário


      // Grava o pagamento no BD
      api.pagamento.list.save(vm.pagamento,
        // Exibe o resultado no console do navegador:
        // Sucesso
        function(response) {
          console.log(response);
        },
        // Erro
        function(response) {
          console.error(response);
        });
    };

    /**
     * Open new contact dialog
     *
     * @param ev
     * @param contact
     */
    // function openContactDialog(ev, contact) {
    //   $mdDialog.show({
    //     controller: 'ContactDialogController',
    //     controllerAs: 'vm',
    //     templateUrl: 'app/main/contacts/dialogs/contact/contact-dialog.html',
    //     parent: angular.element($document.find('#content-container')),
    //     targetEvent: ev,
    //     clickOutsideToClose: true,
    //     locals: {
    //       Contact: contact,
    //       User: vm.user,
    //       Contacts: vm.contacts
    //     }
    //   });
    // }

    /**
     * Delete Contact Confirm Dialog
     */
    // function deleteContactConfirm(contact, ev) {
    //   var confirm = $mdDialog.confirm()
    //     .title('Você tem certeza de que deseja apagar este associado?')
    //     .htmlContent('<b>' + contact.nome + ' (' + contact.cpf + ')</b>' + ' será apagado(a).')
    //     .ariaLabel('apagar contato')
    //     .targetEvent(ev)
    //     .ok('Sim')
    //     .cancel('Cancelar');
    //
    //   $mdDialog.show(confirm).then(function() {
    //     deleteContact(contact);
    //     vm.selectedContacts = [];
    //
    //   }, function() {
    //     console.log('Cancelou');
    //   });
    // }

    /**
     * Delete Contact
     */
    // function deleteContact(contact) {
    //
    //   // TODO Remover também a [formaPgto] do Associado.
    //
    //   // Remove o Associado do BD
    //   console.log('deleteContact @ contacts.controller.js');
    //   api.associado.getByCpf.delete({
    //       'cpf': contact.cpf
    //     },
    //     // Sucesso
    //     function(response) {
    //       console.log(response);
    //     },
    //     // Erro
    //     function(response) {
    //       console.error(response);
    //     }
    //   );
    //
    //   // Remove a da lista na tela a linha deste Associado
    //   vm.contacts.splice(vm.contacts.indexOf(contact), 1);
    // }

    /**
     * Delete Selected Contacts
     */
    // function deleteSelectedContacts(ev) {
    //   var confirm = $mdDialog.confirm()
    //     .title('Você tem certeza de que deseja apagar os associados selecionados?')
    //     .htmlContent('<b>' + vm.selectedContacts.length + ' selecionado(s)</b>' + ' será(ão) apagado(s).')
    //     .ariaLabel('apagar contatos')
    //     .targetEvent(ev)
    //     .ok('Sim')
    //     .cancel('Cancelar');
    //
    //   $mdDialog.show(confirm).then(function() {
    //
    //     vm.selectedContacts.forEach(function(contact) {
    //       deleteContact(contact);
    //     });
    //
    //     vm.selectedContacts = [];
    //
    //   });
    //
    // }

    /**
     * Toggle selected status of the contact
     *
     * @param contact
     * @param event
     */
    // function toggleSelectContact(contact, event) {
    //   if (event) {
    //     event.stopPropagation();
    //   }
    //
    //   if (vm.selectedContacts.indexOf(contact) > -1) {
    //     vm.selectedContacts.splice(vm.selectedContacts.indexOf(contact), 1);
    //   } else {
    //     vm.selectedContacts.push(contact);
    //   }
    // }

    /**
     * Deselect contacts
     */
    // function deselectContacts() {
    //   vm.selectedContacts = [];
    // }

    /**
     * Sselect all contacts
     */
    // function selectAllContacts() {
    //   vm.selectedContacts = $scope.filteredContacts;
    // }

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
