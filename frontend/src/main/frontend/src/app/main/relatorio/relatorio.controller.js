(function ()
{
    'use strict';

    angular
        .module('app.relatorio')
        .controller('RelatorioController', RelatorioController);

    /** @ngInject */
    function RelatorioController($filter ,$scope, $mdSidenav, User, msUtils, $mdDialog, $document, api)
    {
      var vm = this;

      vm.user = User.data;

      // Methods
      vm.limpaForm = limpaForm;
      vm.buscaCpf = buscaCpf;
      vm.buscaPagamentos = buscaPagamentos;
      vm.getTotal = getTotal;

      vm.toggleInArray = msUtils.toggleInArray;
      vm.exists = msUtils.exists;
      //////////

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

      function buscaPagamentos(cpf, dataInicio, dataFim) {
        console.log('buscarPagamentos @ pagamento.controller.js');
        // Temporário

        api.relatPagAssociado.list.query({ //É realizado um filtro da data para atender o esperado no backend
          'cpf': cpf,
          'dataInicio': dataInicio = $filter('date')(dataInicio, 'dd-MM-yyyy'),
          'dataFim': dataFim = $filter('date')(dataFim, 'dd-MM-yyyy')
        },
          // Exibe o resultado no console do navegador:
          // Sucesso
          function(response) {
            console.log(response);
            vm.pagamentos=response;
            vm.getTotal(vm.pagamentos); //Pega o total (soma de todos os pagamentos)
          },
          // Erro
          function(response) {
            console.error(response);
          });
      };


      function getTotal(pagamentos){
        vm.total = 0;
        for(var i = 0; i < pagamentos.length; i++){ //Faz um looping para somar todos os pagamentos
          var valor = pagamentos[i].valor;
          vm.total += valor;
        }
      }
    }
})();
