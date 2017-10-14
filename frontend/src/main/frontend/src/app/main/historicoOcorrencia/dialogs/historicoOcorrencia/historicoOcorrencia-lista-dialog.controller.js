(function() {
  'use strict';

  angular.module('app.historicoOcorrencia').controller('HistoricoOcorrenciaListaDialogController', HistoricoOcorrenciaListaDialogController)

  /** @ngInject */
  function HistoricoOcorrenciaListaDialogController ($mdDialog, $state, Aluno, Ocorrencias, User, msUtils, api) {
    var vm = this;
    vm.title = 'Ocorrencias do Aluno(a) ' + Aluno.nome;
    vm.aluno = angular.copy(Aluno);
    vm.user = User;
    vm.allFields = false;
    vm.api = api;
    vm.ocorrencias = popularLista();

    vm.openManipularOcorrenciaDialog = openManipularOcorrenciaDialog;
    vm.closeDialog = closeDialog;
    vm.deletarTodasAsOcorrencias = deletarTodasAsOcorrencias;

    function popularLista() {
      var lista = [];
      Ocorrencias.forEach(function(oco) {
        if(vm.aluno.ra == oco.raAluno) {
          lista.push(oco);
        }
      })
      return lista;
    }

    function openManipularOcorrenciaDialog (ev, ocorrencia) {
      $mdDialog.show({
        controller: 'ManipularOcorrenciaDialogController',
        controllerAs: 'vm',
        templateUrl: 'app/main/historicoOcorrencia/dialogs/historicoOcorrencia/historicoOcorrencia-manipular-dialog.html',
        parent: (angular.element(document.body)),
        targetEvent: ev,
        clickOutsideToClose: true,
        locals: {
          User: vm.user,
          Ocorrencia: ocorrencia,
          Aluno: vm.aluno,
          api: vm.api
        }
      });
    }

    function deletarTodasAsOcorrencias(ev) {
      var confirm = $mdDialog.confirm().title('Você tem certeza de que deseja apagar TODAS as ocorrencias do aluno?')
                            .htmlContent('<b>As ocorrencias do ' + vm.aluno.nome + ' (' + vm.aluno.ra + ')</b>' + ' serão apagadas.')
                            .ariaLabel('apagar todas as ocorrencias')
                            .targetEvent(ev)
                            .ok('Sim')
                            .cancel('Cancelar');
      $mdDialog.show(confirm).then(function() {
        api.historicoOcorrencia.getByAluno.delete({
          'ra' : vm.aluno.ra
          },
          function(response) {
            console.log(response);
          },
          // Erro
          function(response) {
            console.error(response);
          }
        );

        closeDialog();

      });
    }

    vm.routeReload = function() {
      $state.reload()
    }

    function closeDialog() {
      $mdDialog.hide();
      vm.routeReload();
    }
  }
})();
  