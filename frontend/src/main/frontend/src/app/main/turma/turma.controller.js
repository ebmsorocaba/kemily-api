(function() {
  'use strict';

  angular.module('app.turma').controller('TurmasController', TurmasController);

  /** @ngInject */
  function TurmasController ($scope, $mdSidenav, User, msUtils, $mdDialog, $document, api, $window, Turmas) {

    var vm = this;

    // Data

    vm.turmas = Turmas
    //vm.user = User.data;
    vm.listType = 'all';
    vm.listOrder = 'descricao';
    vm.listOrderAsc = false;
    vm.selectedTurmas = [];


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

    function openTurmaDialog(ev, turma) {
      $mdDialog.show({
        controller: 'TurmaDialogController',
        controllerAs: 'vm',
        templateUrl: 'app/main/turma/dialogs/turma/turma-dialog.html',
        parent: (angular.element(document.body)),
        targetEvent: ev,
        clickOutsideToClose: false,
        locals: {
          Turma: turma,
          User: vm.user,
          Turmas: vm.turmas
        }
      });
    }

    function deleteTurmaConfirm(turma, ev) {
      var confirm = $mdDialog.confirm()
        .title('Você tem certeza de que deseja apagar este turma?')
        .htmlContent('<b>' + turma.descricao + ' (' + turma.id + ')</b>' + ' será apagado(a).')
        .ariaLabel('apagar contato')
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

    function deleteTurma(turma) {
      console.log('deleteTurma @ turma.controller.js');
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
        .ariaLabel('apagar contatos')
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
