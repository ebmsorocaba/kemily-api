(function() {
  'use strict';

  angular
    .module('app.usuario')
    .controller('UsuarioDialogController', UsuarioDialogController);

  /** @ngInject */
  function UsuarioDialogController($mdDialog, Contact, Contacts, User, msUtils, api) {
    var vm = this;

    // Data
    vm.title = 'Alterar Usuário';
    vm.contact = angular.copy(Contact);
    vm.contacts = Contacts;
    vm.user = User;
    vm.newContact = false;
    vm.allFields = false;

    // Formas de Pagamento
    vm.listaSetores = ["Administração", "Financeiro", "Social"];

    // TODO Ajustar o Associado conforme o BackEnd
    if (!vm.contact) {
      vm.contact = {
        'nome': '',
        'senha': '',
        'email': '',
        'setor': '',
        'ativo': ''
      };

      vm.title = 'Novo Usuário';
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
      // Adiciona uma nova linha no topo da lista na tela
      vm.contacts.unshift(vm.contact);

      // Cria o novo registro no BD
      // TODO Tratar de como enviar a [formaPgto] ao BD
      api.usuario.addUsuario.save(vm.contact,
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

      closeDialog();
    }

    /**
     * Save contact
     */
    function saveContact() {
      // Atualiza a linha na tela:
      for (var i = 0; i < vm.contacts.length; i++) {
        if (vm.contacts[i].nome === vm.contact.nome) {
          vm.contacts[i] = angular.copy(vm.contact);
          break;
        }
      }

      // Grava as alterações no BD:
      // TODO UPDATE no BD
      api.usuario.getByNome.update({
        'nome': vm.contact.nome
      },vm.contact,
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
        .title('Você tem certeza de que deseja apagar este usuário?')
        .htmlContent('<b>' + vm.contact.nome + ' (' + vm.contact.nome + '</b>' + ') será apagado(a).')
        .ariaLabel('apagar usuário')
        .targetEvent(ev)
        .ok('OK')
        .cancel('Cancelar');

      $mdDialog.show(confirm).then(function() {

        // TODO Remover também a [formaPgto] do Associado.

        // Remove o Associado do BD
        console.log('deleteContact @ contacts.controller.js');
          api.usuario.getByNome.delete({
            'nome': vm.contact.nome
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
