(function() {
  'use strict';

  angular.module('app.historicoOcorrencia').controller('ManipularOcorrenciaDialogController', ManipularOcorrenciaDialogController)

  /** @ngInject */
  function ManipularOcorrenciaDialogController ($mdDialog, $state, Ocorrencia, User, msUtils, Aluno, api) {
    var vm = this;
    vm.title = 'Manipular Ocorrencia';
    vm.ocorrencia = angular.copy(Ocorrencia);
    vm.user = User;
    vm.allFields = false;

    if(!vm.ocorrencia) {
      vm.ocorrencia = {
        'data': '',
        'hora': '',
        'descricao': '',
        'raAluno': Aluno.ra,
      };
      vm.title = 'Nova Ocorrencia';
    } else {
      //2017-10-14
      var aux = vm.ocorrencia.data;
      vm.ocorrencia.data = aux.substring(8) + '/' + aux.substring(5,7) + '/' + aux.substring(0,5);
      vm.title = 'Alterar Ocorrencia';
    }
    
    if (Ocorrencia !== undefined) {
      vm.newOcorrencia = false
    } else {
      vm.newOcorrencia = true
    }

    vm.closeDialog = closeDialog;
    vm.salvarOcorrencia = salvarOcorrencia;
    vm.adicionarOcorrencia = adicionarOcorrencia;
    vm.deletarOcorrencia = deletarOcorrencia;

    vm.routeReload = function() {
      $state.reload()
    }

    function closeDialog () {
      $mdDialog.hide();
      vm.routeReload();
    }

    function salvarOcorrencia() {
      var aux = vm.ocorrencia.data;
      vm.ocorrencia.data = aux.substring(6,10) + '-' + aux.substring(3,5) + '-' + aux.substring(0,2);
      vm.ocorrencia.hora += ':00';

      api.historicoOcorrencia.ocorrencia.update({
        'ra': vm.ocorrencia.raAluno
        }, vm.ocorrencia,
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

    function adicionarOcorrencia() {
      var aux = vm.ocorrencia.data;
      vm.ocorrencia.data = aux.substring(6,10) + '-' + aux.substring(3,5) + '-' + aux.substring(0,2);  
      vm.ocorrencia.hora = vm.ocorrencia.hora + ':00';

      api.historicoOcorrencia.list.save(vm.ocorrencia,
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

    function deletarOcorrencia(ev, ocorrencia) {
      var confirm = $mdDialog.confirm().title('Você tem certeza de que deseja apagar a ocorrencia?')
                            .htmlContent('<b>As ocorrencia com a data: ' + ocorrencia.data + ' ' + ocorrencia.hora + '</b> será apagada.')
                            .ariaLabel('apagar ocorrencia')
                            .targetEvent(ev)
                            .ok('Sim')
                            .cancel('Cancelar');
      $mdDialog.show(confirm).then(function() {
        var aux = ocorrencia.data;
        ocorrencia.data = aux.substring(6,10) + '-' + aux.substring(3,5) + '-' + aux.substring(0,2);
        ocorrencia.hora += ':00'

        api.historicoOcorrencia.deletarOcorrencia.delete({
          'data': ocorrencia.data,
          'hora': ocorrencia.hora,
          'ra': ocorrencia.raAluno
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

        closeDialog();

      });
    }

  }
})();