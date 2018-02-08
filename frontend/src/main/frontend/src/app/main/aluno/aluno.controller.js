(function() {
  'use strict';

  angular.module('app.aluno').controller('AlunoController', AlunoController);

  /** @ngInject */
  function AlunoController ($scope, $mdSidenav, User, Datas, msUtils, $mdDialog, $document, api, $window) {
    var vm = this;

    // Data
    vm.datas = Datas;
    //vm.user = User.data;
    vm.listType = 'all';
    vm.listOrder = 'nome';
    vm.listOrderAsc = false;
    vm.selectedAlunos = [];

    ordenarListaByStatus();

    // Methods
    vm.openAlunoDialog = openAlunoDialog;
    vm.deleteAlunoConfirm = deleteAlunoConfirm;
    vm.deleteAluno = deleteAluno;
    vm.deleteSelectedAlunos = deleteSelectedAlunos;
    vm.toggleSelectAluno = toggleSelectAluno;
    vm.deselectAlunos = deselectAlunos;
    vm.selectAllAlunos = selectAllAlunos;
    vm.deleteAluno = deleteAluno;
    vm.toggleInArray = msUtils.toggleInArray;
    vm.exists = msUtils.exists;
    vm.exportarExcel = exportarExcel;

    function openAlunoDialog (ev, data) {
      ev.preventDefault()
      $mdDialog.show({
        controller: 'AlunoDialogController',
        controllerAs: 'vm',
        templateUrl: 'app/main/aluno/dialogs/aluno/aluno-dialog.html',
        parent: (angular.element(document.body)),
        targetEvent: ev,
        clickOutsideToClose: false,
        locals: {
          User: vm.user,
          Datas: vm.datas,
          Data: data
        }
      });
    }

    /**
       * Delete Aluno Confirm Dialog
       */
    function deleteAlunoConfirm(aluno, ev) {
      var confirm = $mdDialog.confirm().title('Você tem certeza de que deseja apagar este aluno?').htmlContent('<b>' + aluno.nome + ' (' + aluno.ra + ')</b>' + ' será apagado(a).').ariaLabel('apagar contato').targetEvent(ev).ok('Sim').cancel('Cancelar');

      $mdDialog.show(confirm).then(function() {
        deleteAluno(aluno);
        vm.selectedAlunos = [];

      }, function() {
        //console.log('Cancelou');
      });
    }

    /**
       * Delete Aluno
       */
    function deleteAluno(data) {

      // TODO Remover também a [formaPgto] do Aluno.

      // Remove o Aluno do BD
      console.log('deleteAluno @ alunos.controller.js');
      api.aluno.getByRa.delete({
        'ra': data.aluno.ra
      },
      // Sucesso
      function(response) {
        console.log(response);
      },
      // Erro
      function(response) {
        console.error(response);
      });

      vm.datas.splice(vm.datas.indexOf(data), 1);
    }

    /**
       * Delete Selected Alunos
       */
    function deleteSelectedAlunos(ev) {
      var confirm = $mdDialog.confirm().title('Você tem certeza de que deseja apagar os alunos selecionados?').htmlContent('<b>' + vm.selectedAlunos.length + ' selecionado(s)</b>' + ' será(ão) apagado(s).').ariaLabel('apagar contatos').targetEvent(ev).ok('Sim').cancel('Cancelar');
      $mdDialog.show(confirm).then(function() {

        vm.selectedAlunos.forEach(function(aluno) {
          deleteAluno(aluno);
        });

        vm.selectedAlunos = [];

      });

    }

    function toggleSelectAluno(aluno, event) {
      if (event) {
        event.stopPropagation();
      }
      if (vm.selectedAlunos.indexOf(aluno) > -1) {
        vm.selectedAlunos.splice(vm.selectedAlunos.indexOf(aluno), 1);
      } else {
        vm.selectedAlunos.push(aluno);
      }
    }

    /**
       * Deselect alunos
       */
    function deselectAlunos () {
      vm.selectedAlunos = [];
    }

    /**
       * Sselect all alunos
       */
    function selectAllAlunos () {
      vm.selectedAlunos = $scope.filteredAlunos;
    }

    function exportarExcel() {
      
      window.open('/api/aluno/excel', '_blank');

      /* api.aluno.excel.get(
        function(response) {
          var blob = new Blob([response], {type: 'application/vnd.ms-excel'});
          console.log(blob);
          var objectUrl = URL.createObjectURL(blob);
          $window.open(objectUrl, '_blank');
        },

        function(response) {
          console.error(response);
        }
      ); */
    }

    function ordenarListaByStatus() {
      var listaAtivo = [];
      var listaInativo = [];

      vm.datas.forEach(function(a) {
        if(a.aluno.ativo == true) {
          listaAtivo.push(a);
        } else {
          listaInativo.push(a);
        }
      });

      vm.datas = listaAtivo;
      vm.datas = vm.datas.concat(listaInativo);
    }

  }

})();
