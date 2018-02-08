(function() {
  'use strict';

  angular
    .module('app.usuario')
    .controller('UsuarioDialogController', UsuarioDialogController)
    .directive('compareTo', function() {
      return {
        require: "ngModel",
        scope: {
          otherModelValue: "=compareTo"
        },
        link: function(scope, element, attributes, ngModel) {
            
          ngModel.$validators.compareTo = function(modelValue) {
            return modelValue == scope.otherModelValue;
          };

          scope.$watch("otherModelValue", function() {
            ngModel.$validate();
          });
        }
      };
    }
  );

  /** @ngInject */
  function UsuarioDialogController($mdDialog, Usuario, Usuarios, User, msUtils, api) {
    var vm = this;

    // Data
    vm.title = 'Alterar Usuário';
    vm.usuario = angular.copy(Usuario);
    vm.usuarios = Usuarios;
    vm.user = User;
    vm.newUsuario = false;
    vm.allFields = false;
    
    //variavel utilizada para alterar a senha de um usuário
    vm.newSenha='';

    // Formas de Pagamento
    vm.listaSetores = ["Administração", "Financeiro", "Social"];

    // TODO Ajustar o Associado conforme o BackEnd
    if (!vm.usuario) {
      vm.usuario = {
        'nome': '',
        'senha': '',
        'email': '',
        'setor': '',
        'ativo': ''
      };

      vm.title = 'Novo Usuário';
      vm.newUsuario = true;
      // vm.usuario.tags = [];
    } else {
      
    	//inicializa a variavel como vazia , para nao mostrar o hash no campo
      vm.usuario.confirmaSenha = '';

    }

    // Methods
    vm.addNewUsuario = addNewUsuario;
    vm.saveUsuario = saveUsuario;
    vm.deleteUsuarioConfirm = deleteUsuarioConfirm;
    vm.closeDialog = closeDialog;
    vm.toggleInArray = msUtils.toggleInArray;
    vm.exists = msUtils.exists;
    vm.alterUsuarioConfirm = alterUsuarioConfirm;
    //////////

    /**
     * Add new usuario
     */
    function addNewUsuario() {
      // Cria o novo registro no BD
      // TODO Tratar de como enviar a [formaPgto] ao BD

      api.usuario.addUsuario.save(vm.usuario,
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
      vm.usuarios.unshift(vm.usuario);

      closeDialog();
    }
    
    //função que recebe a confirmação de alteração de um usuário
    //e consequentemente altera o mesmo
    function alterUsuarioConfirm(ev) {
    	//Cria um "Dialog Confirm" para confirmar a execução de alteraçao
    	//do usuário no sistema
        var confirm = $mdDialog.confirm()
          .title('Você tem certeza de que deseja alterar este usuário?')
          .ariaLabel('alterar usuario')
          .targetEvent(ev)
          .ok('Sim')
          .cancel('Cancelar');

        //Verifica a resposta de Confirmação
        //Se "sim" continua, caso "Cancelar" fecha
        $mdDialog.show(confirm).then(function() {
        	//Chama função para salvar o usuário no banco de dados
        	saveUsuario();

        }, function() {
          //console.log('Cancelou');
        });
      }


    /**
     * Save usuario
     */
    function saveUsuario() {
     
    	//verifica se o campo de uma nova senha foi preenchido e adiciona
    	//a nova senha ao usuário
    	if(vm.newSenha != null && vm.newSenha != ''){
    		vm.usuario.senha = vm.newSenha;
    	}
    	
    	 // Atualiza a linha na tela:
      for (var i = 0; i < vm.usuarios.length; i++) {
        if (vm.usuarios[i].nome === vm.usuario.nome) {
          vm.usuarios[i] = angular.copy(vm.usuario);
          break;
        }
      }

      // Grava as alterações no BD:
      api.usuario.getByNome.update({
        'nome': vm.usuario.nome
      },vm.usuario,
        // Exibe o resultado no console do navegador:
        // Sucesso
        function(response) {
    	  
    	  //Cria um Dialog Alert para informar o usuário do sistema
    	  //a alteração feita
    	  var alert = $mdDialog.alert({
    	        title: 'Informação',
    	        textContent: 'O Usuário '+ vm.usuario.nome + ' foi alterado com sucesso !',
    	        ok: 'Fechar'
    	      });
    	  
    	      $mdDialog.show( alert );
    	  
          console.log(response);
        },
        // Erro
        function(response) {
          console.error(response);
        }
      );

      // Dummy save action
      // for ( var i = 0; i < vm.usuarios.length; i++ )
      // {
      //     if ( vm.usuarios[i].id === vm.usuario.id )
      //     {
      //         vm.usuarios[i] = angular.copy(vm.usuario);
      //         break;
      //     }
      // }

      closeDialog();
    }

    /**
     * Delete Usuario Confirm Dialog
     */
    function deleteUsuarioConfirm(ev) {
      var confirm = $mdDialog.confirm()
        .title('Você tem certeza de que deseja apagar este usuário?')
        .htmlContent('<b>' + vm.usuario.nome + ' (' + vm.usuario.nome + '</b>' + ') será apagado(a).')
        .ariaLabel('apagar usuário')
        .targetEvent(ev)
        .ok('OK')
        .cancel('Cancelar');

      $mdDialog.show(confirm).then(function() {

        // TODO Remover também a [formaPgto] do Associado.

        // Remove o Associado do BD
        console.log('deleteUsuario @ usuarios.controller.js');
          api.usuario.getByNome.delete({
            'nome': vm.usuario.nome
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
        vm.usuarios.splice(vm.usuarios.indexOf(Usuario), 1);
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