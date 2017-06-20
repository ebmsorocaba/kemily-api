(function ()
{
    'use strict';

    angular
        .module('app.relatorio')
        .controller('RelatorioController', RelatorioController);

    /** @ngInject */
    function RelatorioController($filter ,$scope, $mdSidenav, User, msUtils, $mdDialog, $document, api, $window)
    {
      var vm = this;

      vm.user = User.data;
      vm.searched = false;



      // Methods
      vm.limpaForm = limpaForm;
      vm.buscaCpf = buscaCpf;


      vm.openAssociadoDialog = openAssociadoDialog

      vm.toggleInArray = msUtils.toggleInArray;
      vm.exists = msUtils.exists;
      vm.buscaPagamentos = buscaPagamentos;
      vm.novaConsulta = novaConsulta;
      vm.exportarExcel = exportarExcel;
      vm.exportarPdf = exportarPdf;
      //////////

      /*
      vm.cpf ='333.333.333-33';
      vm.dataInicio = new Date(2015, 0, 1, 0, 0, 0, 0);
      vm.dataFim = new Date(2015, 3, 1, 0, 0, 0, 0);
      */
      console.log(vm.dataInicio);
      console.log(vm.dataFim);


      function limpaForm() {
        console.log('limpaForm @ pagamento.controller.js');
        vm.cpf='';
        vm.dataInicio='';
        vm.dataFim='';
      }

      function buscaCpf() {
        console.log('buscaCpf @ pagamento.controller.js');
        if (vm.cpf) {
          // Busca o Associado no BD para recuperar a data de pagamento e valor esperados
          api.associado.getByCpf.get({
              'cpf': vm.cpf
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
        };


      }




      function openAssociadoDialog(ev) {
        $mdDialog.show({
          controller: 'RelatorioDialogController',
          controllerAs: 'vm',
          templateUrl: 'app/main/relatorio/dialogs/relatorio/relatorio-dialog.html',
          parent: (angular.element(document.body)),
          //parent: angular.element($document.find('#content-container')),
          targetEvent: ev,
          clickOutsideToClose: true,
          locals: {
            //Cpf: vm.cpf,
            DataInicio: vm.dataInicio,
            DataFim: vm.dataFim
          }

        });
      }


      function buscaPagamentos() {
        console.log('buscarPagamentos @ pagamento.controller.js');
        // Temporário



        api.relatPag.list.query({ //É realizado um filtro da data para atender o esperado no backend
          'dataInicio': vm.dataInicio = $filter('date')(vm.dataInicio, 'dd-MM-yyyy'),
          'dataFim': vm.dataFim = $filter('date')(vm.dataFim, 'dd-MM-yyyy')
        },
          // Exibe o resultado no console do navegador:
          // Sucesso
          function(response) {
            console.log(response);
            vm.pagamentos=response;
            vm.searched=true;
            // vm.getTotal(vm.pagamentos); //Pega o total (soma de todos os pagamentos)
            // vm.dataHeader = DataInicio;
          },
          // Erro
          function(response) {
            console.error(response);
          });

      };

      function novaConsulta() {
        console.log('buscarPagamentos @ pagamento.controller.js');
          vm.searched=false;
          $window.location.href = '/#/relatorio';
      };

      function exportarExcel(){
        var blob = new Blob([document.getElementById('pagamentosTable').innerHTML], {
        type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
        });

        saveAs(blob, vm.dataInicio + "-" + vm.dataFim +".xls");
      };

      function exportarPdf(){
        console.log('exportar');
        // var blob = new Blob([document.getElementById('pagamentosTable').innerHTML], {
        // type: "application/pdf;charset=utf-8"
        // });
        //     saveAs(blob, vm.dataInicio + "-" + vm.dataFim +".pdf");

        // var specialElementHandlers = {
        // 'table': function(element, renderer) {
        //         return true
        //     }
        // };
        // var margins = {
        //     top: 30,
        //     bottom: 60,
        //     left: 60,
        //     width: 700
        // };
        //
        // var options = {pagesplit: true};
        //
        // var pdf = new jsPDF('p', 'pt', 'letter');
        // pdf.fromHTML((document.getElementById('pagamentosTable').innerHTML),
        //     margins.left,
        //     margins.top, {
        //         'width': margins.width,
        //         'elementHandlers': specialElementHandlers
        //     }, options);
        //
        // pdf.save('myfilename' + '.pdf');

        var pdfName = vm.dataInicio + "-" + vm.dataFim;
        var pdf = new jsPDF('l', 'pt', 'a4'),
        specialElementHandlers = {
          'table': function(element, renderer) {
            return true;
          }
        },
        margins = {
            top: 80,
            bottom: 60,
            left: 40,
            width: 30
        };

        var res = pdf.autoTableHtmlToJson(document.getElementById("realTable"));
/*
        pdf.autoTable(res.columns, res.data, {
          pageBreak: 'auto',
          startY: 15,
          tableWidth: 'wrap',
          margin: { horizontal: 0},
          bodyStyles: { valign: 'top' },
          styles: { overflow: 'linebreak', columnWidth: 'wrap' },
          columnStyles: { text: { columnWidth: 'auto' } }
        });
*/

        //pdf.autoTable(res.columns.splice(0, 5), res.data, {
        pdf.autoTable(res.columns, res.data, {
          showHeader: 'everyPage',
          pageBreak: 'aways',
          tableWidth: 'auto',
          styles: {columnWidth: 'auto', overflow: 'linebreak'},
          columnStyles: {
              name: {fillColor: [41, 128, 185], textColor: 255, fontStyle: 'bold'},
              text: {columnWidth: 'auto'}
          },
        });


        pdf.save(pdfName + ".pdf");


      };

    }
})();
