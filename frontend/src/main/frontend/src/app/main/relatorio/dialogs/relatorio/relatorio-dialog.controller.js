(function() {
  'use strict';

  angular
    .module('app.relatorio')
    .controller('RelatorioDialogController', RelatorioDialogController);

  /** @ngInject */
  function RelatorioDialogController($mdDialog, Cpf, DataInicio, DataFim, msUtils, api, $filter) {
    var vm = this;

    // Data
    vm.title = 'Alterar Associado';
    //vm.associado = angular.copy(Associado);
    //vm.associados = Associados;
    //vm.user = User;
    vm.newAssociado = false;
    vm.allFields = false;
    vm.getTotal = getTotal;

    // Formas de Pagamento
    vm.listaPgtos = ["Boleto", "Dinheiro", "Cartão"];

    // TODO Ajustar o Associado conforme o BackEnd
    /*if (!vm.associado) {
      vm.associado = {
        'cpf': '',
        'nome': '',
        'celular': null,
        'email': '',
        // 'formaPgto': 'Dinheiro', // TODO Tirar do objeto (ver [/api/formaPgto])
        // 'cartao': 1231231231231231, // TODO Tirar do objeto (ver [/cartao])
        'valorAtual': null,
        'vencAtual': null,
      };
       */
      vm.title = 'Relatório Financeiro';
      vm.newAssociado = true;
      // vm.associado.tags = [];
    //}

    // Methods
    vm.addNewAssociado = addNewAssociado;
    vm.saveAssociado = saveAssociado;
    vm.deleteAssociadoConfirm = deleteAssociadoConfirm;
    vm.closeDialog = closeDialog;
    vm.toggleInArray = msUtils.toggleInArray;
    vm.exists = msUtils.exists;
    vm.buscaPagamentos = buscaPagamentos;
    //////////

    vm.buscaPagamentos(Cpf, DataInicio, DataFim);

    /**
     * Add new contact
     */
    function addNewAssociado() {
      // Cria o novo registro no BD
      // TODO Tratar de como enviar a [formaPgto] ao BD
      api.associado.list.save(vm.associado,
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
      vm.associados.unshift(vm.associado);

      closeDialog();
    }

    /**
     * Save contact
     */
    function saveAssociado() {
      // Atualiza a linha na tela:
      for (var i = 0; i < vm.associados.length; i++) {
        if (vm.associados[i].cpf === vm.associado.cpf) {
          vm.associados[i] = angular.copy(vm.associado);
          break;
        }
      }

      // Grava as alterações no BD:
      api.associado.getByCpf.save(vm.associado,
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

      // Dummy save action
      // for ( var i = 0; i < vm.contacts.length; i++ )
      // {
      //     if ( vm.contacts[i].id === vm.contact.id )
      //     {
      //         vm.contacts[i] = angular.copy(vm.contact);
      //         break;
      //     }
      // }

      closeDialog();
    }

    /**
     * Delete Contact Confirm Dialog
     */
    function deleteAssociadoConfirm(ev) {
      var confirm = $mdDialog.confirm()
        .title('Você tem certeza de que deseja apagar este associado?')
        .htmlContent('<b>' + vm.associado.nome + ' (' + vm.associado.cpf + '</b>' + ') será apagado(a).')
        .ariaLabel('apagar associado')
        .targetEvent(ev)
        .ok('OK')
        .cancel('Cancelar');

      $mdDialog.show(confirm).then(function() {

        // TODO Remover também a [formaPgto] do Associado.

        // Remove o Associado do BD
        console.log('deleteAssociado @ associados.controller.js');
        api.associado.getByCpf.delete({
            'cpf': associado.cpf
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

        // Remove a da lista na tela a linha deste Associado
        vm.associados.splice(vm.associados.indexOf(Associado), 1);
      });
    }

    /**
     * Close dialog
     */
    function closeDialog() {
      $mdDialog.hide();
    }


    function getTotal(pagamentos){
      vm.total = 0;
      for(var i = 0; i < pagamentos.length; i++){ //Faz um looping para somar todos os pagamentos
        var valor = pagamentos[i].valor;
        vm.total += valor;
      }
    }


    function buscaPagamentos(Cpf, DataInicio, DataFim) {
      console.log('buscarPagamentos @ pagamento.controller.js');
      // Temporário

      api.relatPagAssociado.list.query({ //É realizado um filtro da data para atender o esperado no backend
        'cpf': Cpf,
        'dataInicio': DataInicio = $filter('date')(DataInicio, 'dd-MM-yyyy'),
        'dataFim': DataFim = $filter('date')(DataFim, 'dd-MM-yyyy')
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



  }
})();
