(function() {
  'use strict';

  angular.module('app.turma').controller('TurmaDialogController', TurmaDialogController)

  /** @ngInject */
  function TurmaDialogController ($mdDialog, $state, Turma, Turmas, User, msUtils, api) {
    var vm = this;
    vm.title = 'Alterar Turma';
    vm.turma = angular.copy(Turma);
    vm.user = User;
    vm.allFields = false;

    if(!vm.turma) {
      vm.turma = {
        'id': '',
        'descricao': '',
        'cpfEducador': '',
      };
      vm.title = 'Novo Turma';
    } else {
      vm.turma.dataNasc = new Date(vm.turma.dataNasc);
      vm.title = 'Alterar Turma';
    }
    // Methods
    if (Turma !== undefined) {
      vm.newTurma = false
    } else {
      vm.newTurma = true
    }

    vm.turmas = Turmas
    vm.addNewTurma = addNewTurma;
    vm.saveTurma = saveTurma;
    vm.closeDialog = closeDialog;
    vm.deleteTurmaConfirm = deleteTurmaConfirm;
    vm.toggleInArray = msUtils.toggleInArray;
    vm.exists = msUtils.exists;
    //////////

    /**
     * Add new turma
     */
    function addNewTurma() {
      // Cria o novo registro no BD
      // TODO Tratar de como enviar a [formaPgto] ao BD
      //if(vm.ok == true){
        api.turma.list.save(vm.turma,
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
        vm.turmas.unshift(vm.turma);

        closeDialog();
      //}
    }

    vm.routeReload = function() {
      $state.reload()
      // $window.location.reload()
    }

    function closeDialog () {
      $mdDialog.hide()
    }

    /**
     * Save new turma
     */
    function saveTurma() {
      // Atualiza a linha na tela:
      for (var i = 0; i < vm.turmas.length; i++) {
        if (vm.turmas[i].id === vm.turma.id) {
          vm.turmas[i] = angular.copy(vm.turma);
          break;
        }
      }

      // Grava as alterações no BD:
      api.turma.getById.update({
        'id': vm.turma.id
      },vm.turma,
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

    function deleteTurmaConfirm(ev) {
      var confirm = $mdDialog.confirm()
        .title('Você tem certeza de que deseja apagar este turma?')
        .htmlContent('<b>' + vm.turma.descricao + ' (' + vm.turma.id + '</b>' + ') será apagado(a).')
        .ariaLabel('apagar turma')
        .targetEvent(ev)
        .ok('OK')
        .cancel('Cancelar');

      $mdDialog.show(confirm).then(function() {

        console.log('deleteTurma @ turma.controller.js');
        api.turma.getById.delete({
            'id': vm.turma.id
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

        vm.turmaes.splice(vm.turmaes.indexOf(Turma), 1);
      });
    }
  }
})();
