(function() {
  'use strict';

  angular
    .module('app.contacts')
    .controller('ContactDialogController', ContactDialogController);

  /** @ngInject */
  function ContactDialogController($mdDialog, Contact, Contacts, User, msUtils, api) {
    var vm = this;

    // Data
    vm.title = 'Alterar Associado';
    vm.contact = angular.copy(Contact);
    vm.contacts = Contacts;
    vm.user = User;
    vm.newContact = false;
    vm.allFields = false;

    // Formas de Pagamento
    vm.listaPgtos = ["Boleto", "Dinheiro", "Cartão"];

    // TODO Ajustar o Associado conforme o BackEnd
    if (!vm.contact) {
      vm.contact = {
        'cpf': '',
        'nome': '',
        'celular': null,
        'email': '',
        // 'formaPgto': 'Dinheiro', // TODO Tirar do objeto (ver [/api/formaPgto])
        // 'cartao': 1231231231231231, // TODO Tirar do objeto (ver [/cartao])
        'valorAtual': null,
        'vencAtual': null,
      };

      vm.title = 'Novo Associado';
      vm.newContact = true;
      // vm.contact.tags = [];
    }

    // Methods
    vm.addNewContact = addNewContact;
    vm.saveContact = saveContact;
    vm.deleteContactConfirm = deleteContactConfirm;
    vm.closeDialog = closeDialog;
    vm.toggleInArray = msUtils.toggleInArray;
    vm.exists = msUtils.exists;
    //////////

    /**
     * Add new contact
     */
    function addNewContact() {
<<<<<<< HEAD
=======
      // Adiciona uma nova linha no topo da lista na tela
      vm.contacts.unshift(vm.contact);

>>>>>>> fdde622372f11a90f1bdd0598eef534624bf70d7
      // Cria o novo registro no BD
      // TODO Tratar de como enviar a [formaPgto] ao BD
      api.associado.list.save(vm.contact,
        // Exibe o resultado no console do navegador:
        // Sucesso
        function(response) {
          console.log(response);
        },
        // Erro
        function(response) {
          console.error(response);
        }
      );

      // Adiciona uma nova linha no topo da lista na tela
      vm.contacts.unshift(vm.contact);

      closeDialog();
    }

    /**
     * Save contact
     */
    function saveContact() {
      // Atualiza a linha na tela:
      for (var i = 0; i < vm.contacts.length; i++) {
        if (vm.contacts[i].cpf === vm.contact.cpf) {
          vm.contacts[i] = angular.copy(vm.contact);
          break;
        }
      }

      // Grava as alterações no BD:
      api.associado.getByCpf.save(vm.contact,
        // Exibe o resultado no console do navegador:
        // Sucesso
        function(response) {
          console.log(response);
        },
        // Erro
        function(response) {
          console.error(response);
        }
      );

      // Dummy save action
      // for ( var i = 0; i < vm.contacts.length; i++ )
      // {
      //     if ( vm.contacts[i].id === vm.contact.id )
      //     {
      //         vm.contacts[i] = angular.copy(vm.contact);
      //         break;
      //     }
      // }

      closeDialog();
    }

    /**
     * Delete Contact Confirm Dialog
     */
    function deleteContactConfirm(ev) {
      var confirm = $mdDialog.confirm()
        .title('Você tem certeza de que deseja apagar este associado?')
        .htmlContent('<b>' + vm.contact.nome + ' (' + vm.contact.cpf + '</b>' + ') será apagado(a).')
        .ariaLabel('apagar associado')
        .targetEvent(ev)
        .ok('OK')
        .cancel('Cancelar');

      $mdDialog.show(confirm).then(function() {

        // TODO Remover também a [formaPgto] do Associado.

        // Remove o Associado do BD
        console.log('deleteContact @ contacts.controller.js');
        api.associado.getByCpf.delete({
            'cpf': contact.cpf
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
        vm.contacts.splice(vm.contacts.indexOf(Contact), 1);
      });
    }

    /**
     * Close dialog
     */
    function closeDialog() {
      $mdDialog.hide();
    }

  }
})();
