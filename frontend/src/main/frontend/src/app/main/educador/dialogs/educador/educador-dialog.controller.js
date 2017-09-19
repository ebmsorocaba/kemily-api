(function() {
  'use strict';

  angular.module('app.educador').controller('EducadorDialogController', EducadorDialogController)

  /** @ngInject */
  function EducadorDialogController ($mdDialog, Educador, Educadores, User, msUtils, api) {
    var vm = this;
    vm.title = 'Alterar Educador';
    vm.educador = angular.copy(Educador);
    vm.user = User;
    vm.allFields = false;

    vm.sexos = [
      'Masculino',
      'Feminino'
    ]

    vm.cargos = [
      'Auxiliar',
      'Educador',
      'Voluntário'
    ];

    if(!vm.educador) {
      vm.educador = {
        'cpf': '',
        'nome': '',
        'sexo': '',
        'telefone': '',
        'dataNasc': '',
        'email': '',
        'cargo': '',
        'numeroCarteiraProfissional': '',
        'serieCarteiraProfissional': '',
        'numeroPis': ''
      };
      vm.title = 'Novo Educador';
    } else {
      vm.educador.dataNasc = new Date(vm.educador.dataNasc);
      vm.title = 'Alterar Educador';
    }
    // Methods
    if (Educador !== undefined) {
      vm.newEducador = false
    } else {
      vm.newEducador = true
    }

    vm.educadores = Educadores
    vm.addNewEducador = addNewEducador;
    vm.saveEducador = saveEducador;
    vm.deleteEducadorConfirm = deleteEducadorConfirm;
    vm.closeDialog = closeDialog;
    vm.toggleInArray = msUtils.toggleInArray;
    vm.exists = msUtils.exists;
    //////////

    /**
     * Add new educador
     */
    function addNewEducador() {
      // Cria o novo registro no BD
      // TODO Tratar de como enviar a [formaPgto] ao BD
      //if(vm.ok == true){
        api.educador.list.save(vm.educador,
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
        vm.educadores.unshift(vm.educador);

        closeDialog();
      //}
    }

    function closeDialog () {
      $mdDialog.hide()
    }

    /**
     * Save new educador
     */
    function saveEducador() {
      // Atualiza a linha na tela:
      for (var i = 0; i < vm.educadores.length; i++) {
        if (vm.educadores[i].cpf === vm.educador.cpf) {
          vm.educadores[i] = angular.copy(vm.educador);
          break;
        }
      }

      // Grava as alterações no BD:
      api.educador.getByCpf.update({
        'cpf': vm.educador.cpf
      },vm.educador,
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

      closeDialog()
    }

    function deleteEducadorConfirm(ev) {
      var confirm = $mdDialog.confirm()
        .title('Você tem certeza de que deseja apagar este educador?')
        .htmlContent('<b>' + vm.educador.nome + ' (' + vm.educador.cpf + '</b>' + ') será apagado(a).')
        .ariaLabel('apagar educador')
        .targetEvent(ev)
        .ok('OK')
        .cancel('Cancelar');

      $mdDialog.show(confirm).then(function() {

        console.log('deleteEducador @ educador.controller.js');
        api.educador.getByCpf.delete({
            'cpf': vm.educador.cpf
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

        vm.educadores.splice(vm.educadores.indexOf(Educador), 1);
      });
    }
  }
})();
