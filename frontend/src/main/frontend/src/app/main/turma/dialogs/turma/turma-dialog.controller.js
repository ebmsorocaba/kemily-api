(function() {
  'use strict';

  angular.module('app.turma').controller('TurmaDialogController', TurmaDialogController)

  /** @ngInject */
  function TurmaDialogController ($mdDialog, $state, Turma, Turmas, AlunoTurma, User, msUtils, api, AlunosDentroTurma, AlunosForaTurma) {
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
    vm.removerAluno = removerAluno;
    vm.adicionarAluno = adicionarAluno;
    vm.alunoTurma = AlunoTurma;
    vm.alunosDentroTurma = AlunosDentroTurma;
    vm.alunosForaTurma = AlunosForaTurma;
    console.log('-----');
    console.log(vm.alunoTurma);
    console.log(vm.alunosDentroTurma);
    console.log(vm.alunosForaTurma);


    function removerAluno(a) {
      // Cria o novo registro no BD
      // TODO Tratar de como enviar a [formaPgto] ao BD
      //if(vm.ok == true){
        api.alunoTurma.removeAluno.delete({
          'ra': a.aluno.ra,
          'id': Turma.id
        },
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

        vm.alunosForaTurma.push(a);
        vm.alunosDentroTurma.splice(vm.alunosDentroTurma.indexOf(a, 1));
    }

    function adicionarAluno(a) {
      var lista = [];
      var at = {
        'raAluno': a.aluno.ra,
        'idTurma': Turma.id
      };
      lista.push(at);
      console.log(at);

      api.alunoTurma.list.save(lista,
        function(response) {
          console.log(response);
        },
        // Erro
        function(response) {
          console.error(response);
        }
      );

      vm.alunosDentroTurma.push(a);
      vm.alunosForaTurma.splice(vm.alunosForaTurma.indexOf(a, 1));
    }
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
      $mdDialog.hide();
      vm.routeReload();
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

        console.log('deleteTurma @ turma-dialog.controller.js');
        api.alunoTurma.getById.delete({
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

        vm.turmas.splice(vm.turmas.indexOf(vm.turma), 1);
      });
    }
  }
})();
