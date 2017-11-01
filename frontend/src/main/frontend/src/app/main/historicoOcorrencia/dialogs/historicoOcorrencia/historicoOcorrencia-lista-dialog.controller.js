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
    $filter,
    $state,
    Aluno,
    Ocorrencias,
    User,
    msUtils,
    api
  ) {
    var vm = this;
    vm.title = "Aluno(a): " + Aluno.nome;
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
    vm.resetarValores = resetarValores;
    vm.exportarExcel = exportarExcel

    vm.filtros = [
      'Somente um Mês',
      'Entre dois ou mais Meses do Mesmo Ano',
      'Entre Meses e Anos'
    ];

    vm.meses = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];

    function formataData(data) {
      var aux = new Date(data);
      aux.setDate(aux.getDate() + 1)
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

    function exportarExcel(nome){
      var blob = new Blob(["\ufeff", document.getElementById('ocorrenciasTable').innerHTML], {
      type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8"
      });

      saveAs(blob,  Aluno.nome + " - Historico" +".xls");
    };

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

    function filtrarOcorrencias(mesInicial, mesFinal, anoInicial, anoFinal, tipoFiltro) {
      debugger;
      vm.ocorrencias = popularLista();
      var filtro = [];
      mesInicial = mesInicial*1;
      mesFinal = mesFinal*1;

      if (tipoFiltro === 'Somente um Mês') {

        if (anoInicial === '' || anoInicial === null || anoInicial === undefined) {
          anoInicial = new Date().getFullYear();
        }

        if (mesInicial >= 1 && mesInicial <= 12) {
          vm.ocorrencias.forEach(function(oco) {

            if(mesInicial === 10) {
              console.log('Ai meu cu');
            }
            var mes = new Date(oco.data).getMonth() + 1;
            var ano = new Date(oco.data).getFullYear();

            if (mes === mesInicial && ano === anoInicial) {
              filtro.push(oco);
            }
          });
        }

      }

      if (tipoFiltro === 'Entre dois ou mais Meses do Mesmo Ano') {

        if (anoInicial === '' || anoInicial === null || anoInicial === undefined) {
          anoInicial = new Date().getFullYear();
        }

        if (
          (mesInicial < mesFinal) &&
          (mesInicial >= 1 && mesInicial <= 12) &&
          (mesFinal >= 1 && mesFinal <= 12)
        ) {
          vm.ocorrencias.forEach(function(oco) {
            var mes = new Date(oco.data).getMonth() + 1;
            var ano = new Date(oco.data).getFullYear();

            if (mes >= mesInicial && mes <= mesFinal && ano === anoInicial) {
              filtro.push(oco);
            }
          });
        }

      }

      if (tipoFiltro === 'Entre Meses e Anos') {

        if (
          (mesInicial !== '' && mesInicial !== null && mesInicial !== undefined) &&
          (mesFinal !== '' && mesFinal !== null && mesFinal !== undefined) &&
          (anoInicial !== '' && anoInicial !== null && anoInicial !== undefined) &&
          (anoFinal !== '' && anoFinal !== null && anoFinal !== undefined)
        ) {

          if (anoInicial < anoFinal) {

            vm.ocorrencias.forEach(function(oco) {
              var data = new Date(oco.data);
              data.setDate(1);
              data.setHours(0);
              data.setMinutes(0);

              var dataInicial = new Date(anoInicial, mesInicial - 1);
              dataInicial.setHours(0);
              dataInicial.setMinutes(0);

              var dataFinal = new Date(anoFinal, mesFinal - 1);
              dataFinal.setHours(0);
              dataFinal.setMinutes(0);

              if (data >= dataInicial && data <= dataFinal) {
                filtro.push(oco);
              }
            });

          }

        }

      }

      vm.ocorrencias = filtro;
    }

    function resetarFiltro() {
      vm.mesInicial = '';
      vm.mesFinal = '';
      vm.anoInicial = '';
      vm.anoFinal = '';
      vm.tipoFiltro = '';
      vm.ocorrencias = popularLista();
    }

    function resetarValores() {
      vm.mesInicial = '';
      vm.mesFinal = '';
      vm.anoInicial = '';
      vm.anoFinal = '';
      vm.ocorrencias = popularLista();
    }
  }
})();
