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

    // TODO Ajustar objetos conforme o backend
    if (!vm.contact) {
      vm.contact = {
        'cpf': 111,
        'nome': 'Teste',
        'celular': 111,
        'email': 'a@a.a',
        // 'formaPgto': 'Cartão', // Tirar do objeto
        // 'cartao': 1231231231231231, // Tirar do objeto
        'valorAtual': 1.3,
        'vencAtual': 5,
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
      // Adiciona uma nova linha no topo da lista (temporariamente):
      vm.contacts.unshift(vm.contact);

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
      // TODO UPDATE no BD
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

        // TODO Apagar o registro no BD

        // Retorna um novo [array] sem o associado removido
        
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
