(function() {
  "use strict";

  angular
  .module("app.historicoOcorrencia")
  .controller(
    "HistoricoOcorrenciaListaDialogController",
    HistoricoOcorrenciaListaDialogController
  );

  /** @ngInject */
  function HistoricoOcorrenciaListaDialogController(
    $mdDialog,
    $state,
    Aluno,
    Ocorrencias,
    User,
    msUtils,
    api
  ) {
    var vm = this;
    vm.title = "Ocorrencias do Aluno(a) " + Aluno.nome;
    vm.aluno = angular.copy(Aluno);
    vm.user = User;
    vm.allFields = false;
    vm.api = api;
    vm.ocorrencias = popularLista();

    vm.openManipularOcorrenciaDialog = openManipularOcorrenciaDialog;
    vm.closeDialog = closeDialog;
    vm.deletarTodasAsOcorrencias = deletarTodasAsOcorrencias;
    vm.formataData = formataData;
    vm.formataHora = formataHora;
    vm.filtrarOcorrencias = filtrarOcorrencias;
    vm.resetarFiltro = resetarFiltro;

    function formataData(data) {
      var aux = new Date(data);
      aux.setDate(aux.getDate() + 2)
      return aux.toLocaleDateString("pt-BR");
    };

    function formataHora(hora) {
      var aux = hora
      return aux.replace(/\:\d\d$/, '');
    }

    function popularLista() {
      var lista = Ocorrencias.filter(function(oco) {
        return vm.aluno.ra === oco.raAluno;
      });

      lista.forEach(function(elem) {
        elem.dataSearch = formataData(elem.data);
        elem.horaSearch = formataHora(elem.hora);
      });
      return lista;
    }

    function openManipularOcorrenciaDialog(ev, ocorrencia) {
      $mdDialog.show({
        controller: "ManipularOcorrenciaDialogController",
        controllerAs: "vm",
        templateUrl: "app/main/historicoOcorrencia/dialogs/historicoOcorrencia/historicoOcorrencia-manipular-dialog.html",
        parent: angular.element(document.body),
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
      var confirm = $mdDialog
      .confirm()
      .title(
        "Você tem certeza de que deseja apagar TODAS as ocorrencias do aluno?"
      )
      .htmlContent(
        "<b>As ocorrencias do " +
        vm.aluno.nome +
        " (" +
        vm.aluno.ra +
        ")</b>" +
        " serão apagadas."
      )
      .ariaLabel("apagar todas as ocorrencias")
      .targetEvent(ev)
      .ok("Sim")
      .cancel("Cancelar");
      $mdDialog.show(confirm).then(function() {
        api.historicoOcorrencia.getByAluno.delete({
          ra: vm.aluno.ra
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
    $state.reload();
  };

  function closeDialog() {
    $mdDialog.hide();
    vm.routeReload();
  }

  function filtrarOcorrencias(mesInicial, mesFinal) {
    vm.ocorrencias = popularLista();
    var filtro = [];
    if(mesFinal !== undefined && mesFinal !== '' && mesFinal !== null) {
      if(
        (mesInicial < mesFinal) && 
        (mesInicial >= 1 && mesInicial <= 12) && 
        (mesFinal >= 1 && mesFinal <= 12)
      ) {
        vm.ocorrencias.forEach(function (oco) {
          var temp = new Date(oco.data);
          var mes = temp.getMonth() + 1;
          if(mes >= mesInicial && mes <= mesFinal) {
            filtro.push(oco);
          }
        }); 
      } 
    } else {
      if(mesInicial >= 1 && mesInicial <= 12) {
        vm.ocorrencias.forEach(function (oco) {
          var temp = new Date(oco.data);
          var mes = temp.getMonth() + 1;
          if(mes == mesInicial) {
            filtro.push(oco);
          }
        }); 
      }
    }
    vm.ocorrencias = filtro;
  }

  function resetarFiltro() {
    vm.mesInicial = '';
    vm.mesFinal = '';
    vm.ocorrencias = popularLista();
  }
}
})();
