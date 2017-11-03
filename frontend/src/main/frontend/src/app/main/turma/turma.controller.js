(function() {
  'use strict';

  angular.module('app.turma').controller('TurmasController', TurmasController);

  /** @ngInject */
  function TurmasController ($scope, $mdSidenav, User, msUtils, $mdDialog, $document, api, $window, Turmas, AlunoTurma, Alunos, Educadores) {

    var vm = this;

    // Data

    vm.turmas = Turmas
    vm.listType = 'all';
    vm.listOrder = 'nome';
    vm.listOrderAsc = false;
    vm.selectedTurmas = [];
    vm.alunoTurma = AlunoTurma;
    vm.alunos = Alunos;
    vm.educadores = Educadores;

    // Methods
    vm.openTurmaDialog = openTurmaDialog;
    vm.deleteTurmaConfirm = deleteTurmaConfirm;
    vm.deleteTurma = deleteTurma;
    vm.deleteSelectedTurmas = deleteSelectedTurmas;
    vm.toggleSelectTurma = toggleSelectTurma;
    vm.deselectTurmas = deselectTurmas;
    vm.selectAllTurmas = selectAllTurmas;
    vm.deleteTurma = deleteTurma;
    vm.toggleInArray = msUtils.toggleInArray;
    vm.exists = msUtils.exists;
    vm.selectAlunoTurma = selectAlunoTurma;
    vm.alunosNaTurma = alunosNaTurma;
    vm.alunosForaTurma = alunosForaTurma;


    function openTurmaDialog(ev, turma) {
      $mdDialog.show({
        controller: 'TurmaDialogController',
        controllerAs: 'vm',
        templateUrl: 'app/main/turma/dialogs/turma/turma-dialog.html',
        parent: (angular.element(document.body)),
        targetEvent: ev,
        clickOutsideToClose: false,
        escapeToClose: false,
        locals: {
          Turma: turma,
          User: vm.user,
          Turmas: vm.turmas,
          AlunoTurma: vm.selectAlunoTurma(turma),
          AlunosDentroTurma: vm.alunosNaTurma(turma),
          AlunosForaTurma: vm.alunosForaTurma(turma),
          Educadores: vm.educadores
        }
      });
    }

    function deleteTurmaConfirm(turma, ev) {
      var confirm = $mdDialog.confirm()
        .title('Você tem certeza de que deseja apagar este turma?')
        .htmlContent('<b>' + turma.nome + ' (' + turma.id + ')</b>' + ' será apagado(a).')
        .ariaLabel('apagar turma')
        .targetEvent(ev)
        .ok('Sim')
        .cancel('Cancelar');

      $mdDialog.show(confirm).then(function() {
        deleteTurma(turma);
        vm.selectedTurmas = [];

      }, function() {
        //console.log('Cancelou');
      });
    }

    function selectAlunoTurma(turma) {
      var result = [];
      if(turma !== undefined) {
        result = vm.alunoTurma.filter(function(el) {
          return el.idTurma === turma.id;
        });
      }
      return result;
    }

    function alunosNaTurma(turma) {
      var lista = [];

      if(turma !== undefined) {
        var alunoTurma = vm.selectAlunoTurma(turma);
        var i;
        var j;

        for(i = 0; i < alunoTurma.length; i++) {
          for(j = 0; j < vm.alunos.length; j++) {
            if(alunoTurma[i].raAluno === vm.alunos[j].aluno.ra) {
              lista.push(vm.alunos[j]);
            }
          }
        }
      }

      return lista;
    }

    function alunosForaTurma(turma) {
      var lista = [];

      if(turma !== undefined) {
        var alunoTurma = vm.selectAlunoTurma(turma);
        var i;
        var j;

        for(i = 0; i < vm.alunos.length; i++) {
          lista.push(vm.alunos[i]);
        }

        for(i = 0; i < alunoTurma.length; i++) {
          for(j = 0; j < lista.length; j++) {
            if(alunoTurma[i].raAluno === lista[j].aluno.ra) {
              lista.splice(j, 1);
            }
          }
        }
      }

      return lista;
    }

    function deleteTurma(turma) {
      console.log('deleteTurma @ turma.controller.js');
      console.log('tentando deletar aluno_turma');
      api.alunoTurma.getById.delete({
          'id': turma.id
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
          'id': turma.id
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
      vm.turmas.splice(vm.turmas.indexOf(turma), 1);
    }

    function deleteSelectedTurmas(ev) {
      var confirm = $mdDialog.confirm()
        .title('Você tem certeza de que deseja apagar os turmas selecionados?')
        .htmlContent('<b>' + vm.selectedTurmas.length + ' selecionado(s)</b>' + ' será(ão) apagado(s).')
        .ariaLabel('apagar turmas')
        .targetEvent(ev)
        .ok('Sim')
        .cancel('Cancelar');

      $mdDialog.show(confirm).then(function() {

        vm.selectedTurmas.forEach(function(turma) {
          deleteTurma(turma);
        });

        vm.selectedTurmas = [];

      });

    }

    function toggleSelectTurma(turma, event) {
      if (event) {
        event.stopPropagation();
      }

      if (vm.selectedTurmas.indexOf(turma) > -1) {
        vm.selectedTurmas.splice(vm.selectedTurmas.indexOf(turma), 1);
      } else {
        vm.selectedTurmas.push(turma);
      }
    }
    function deselectTurmas() {
      vm.selectedTurmas = [];
    }
    function selectAllTurmas() {
      vm.selectedTurmas = $scope.filteredTurmas;
    }



  }

})();
