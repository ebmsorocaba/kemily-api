(function() {
  'use strict';

  angular
    .module('app.pagamento')
    .controller('PagamentoDialogController', PagamentoDialogController);

  /** @ngInject */
  function PagamentoDialogController($filter, $mdDialog, Associado, Pagamentos, User, msUtils, api, $scope, $state) {
    var vm = this;

    // Data
    vm.title = 'Alterar Pagamento';
    vm.associado = angular.copy(Associado);
    vm.pagamentos = Pagamentos;
    vm.user = User;
    vm.newAssociado = false;
    vm.allFields = false;
    vm.ok = false;
    vm.associado2 = null;



    // Formas de Pagamento
    vm.listaPgtos = ["Boleto", "Dinheiro", "Cartão"];
    vm.calculaCPF = calculaCPF;


    // TODO Ajustar o Associado conforme o BackEnd
    if (!vm.associado) {
      vm.associado = {
        'valorPago': null,
        'vencimento': new Date(),
        'dataPgto': new Date(),
        'formaPgto': {
          'associado': {
            'cpf': '',
          },
          'formaPagamento': "Dinheiro",
        },
      };

      vm.title = 'Informar Pagamento';
      vm.newAssociado = true;
      // vm.associado.tags = [];
    }
    else {
      vm.associado.dataPgto = new Date(vm.associado.dataPgto);
    }

    // Methods
    vm.addNewPagamento = addNewPagamento;
    vm.saveAssociado = saveAssociado;
    vm.deleteAssociadoConfirm = deleteAssociadoConfirm;
    vm.closeDialog = closeDialog;
    vm.toggleInArray = msUtils.toggleInArray;
    vm.exists = msUtils.exists;

    vm.buscaCpf = buscaCpf;
    vm.cadastrarPagamento = cadastrarPagamento;
    vm.sucess = sucess;
    vm.fail = fail;
    //////////

    /**
     * Add new associado
     */
    function addNewPagamento(ev) {
      // Cria o novo registro no BD
      // TODO Tratar de como enviar a [formaPgto] ao BD
      //if(vm.ok == true){
        api.pagamento.list.save(vm.associado,
          // Exibe o resultado no console do navegador:
          // Sucesso
          function(response) {
            console.log(response);
            vm.sucess(ev);
          },
          // Erro
          function(response) {
            console.error(response);
            vm.fail(ev);
          }
        );

      // Adiciona uma nova linha no topo da lista na tela
        vm.pagamentos.unshift(vm.associado);

        closeDialog();
      //}
    }

    /**
     * Save new associado
     */
    function saveAssociado() {
      // Atualiza a linha na tela:
      for (var i = 0; i < vm.pagamentos.length; i++) {
        if (vm.pagamentos[i].id === vm.associado.id) {
          vm.pagamentos[i] = angular.copy(vm.associado);
          break;
        }
      }

      // Grava as alterações no BD:
      api.pagamento.getById.update({
        'id': vm.associado.id
      },vm.associado,
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

    /**
     * Delete Associado Confirm Dialog
     */
    function deleteAssociadoConfirm(ev) {
      var confirm = $mdDialog.confirm()
        .title('Você tem certeza de que deseja apagar este associado?')
        .htmlContent('<b>' + 'Nº pagamento: ' + '</b>' + vm.associado.id + '</br>' + '<b>' + 'Valor: ' + '</b>' + ($filter('currency')(vm.associado.valorPago, 'R$ '))+ '</br>' + ' será apagado.')
        .ariaLabel('apagar associado')
        .targetEvent(ev)
        .ok('OK')
        .cancel('Cancelar');

      $mdDialog.show(confirm).then(function() {

        // TODO Remover também a [formaPgto] do Associado.

        // Remove o Associado do BD
        console.log('deleteAssociado @ associados.controller.js');
        api.pagamento.getById.delete({
            'id': vm.associado.id
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
        vm.pagamentos.splice(vm.pagamentos.indexOf(Associado), 1);
      });
    }

    function cadastrarPagamento(ev) {
      console.log('cadastrarPagamento @ pagamento.controller.js');
      // Temporário


      // Grava o pagamento no BD
      api.pagamento.list.save(vm.pagamento,
        // Exibe o resultado no console do navegador:
        // Sucesso
        function(response) {
          console.log(response);
          vm.sucess(ev);
        },
        // Erro
        function(response) {
          console.error(response);
          vm.fail(ev);
        });
    };

    function sucess(ev) {
      // Appending dialog to document.body to cover sidenav in docs app
        var confirm = $mdDialog.alert()
              .title('SUCESSO')
              .textContent('O pagamento foi cadastrado com sucesso')
              .ariaLabel('Tudo ok!')
              .targetEvent(ev)
              .ok('OK!')

        $mdDialog.show(confirm).then(function() {
          $scope.status = 'OK!';
          //$state.reload();
        });
    };

    function fail(ev) {
    // Appending dialog to document.body to cover sidenav in docs app
      var confirm = $mdDialog.alert()
            .title('FALHA')
            .textContent('Houve algum problema e pagamento não foi registrado, verifique os campos ou contate um administrador')
            .ariaLabel('Vou verificar!')
            .targetEvent(ev)
            .ok('Vou verificar!')

      $mdDialog.show(confirm).then(function() {
        $scope.status = 'falha!';

      });
    };

    function buscaCpf() {
      console.log('buscaCpf @ pagamento.controller.js');
      if (vm.associado.formaPgto.associado.cpf) {
        // Busca o Associado no BD para recuperar a data de pagamento e valor esperados
        api.associado.getByCpf.get({
            'cpf': vm.associado.formaPgto.associado.cpf
          },
          // Sucesso
          function(response) {
            console.log(response);
            vm.associado2 = response;

            // Define o valor esperado
            vm.associado.valorPago = vm.associado2.valorAtual;

            // Define a data de pagamento esperada
            vm.associado.vencimento.setDate(vm.associado2.vencAtual);
          },
          // Erro
          function(response) {
            console.error(response);
          }
        );
      };
    }

    function calculaCPF(strCPF) {
      var Soma;
      var Resto;
      Soma = 0;
      var i;
      var flag = 0;

      //retirar mascara
      if(strCPF != null){
        strCPF = strCPF.replace(/\-/g,"");
        strCPF = strCPF.replace(/\./g,"");
      }
      else{
        return false;
      }

      //verificar se os numeros do cpf são todos iguais ex: 000.000.000-00
      for(i=0; i<11; i++){
        if(strCPF[i] == strCPF[i+1]){
          flag++;
        }
      }

      if(flag==10){
        vm.ok = false;
        return false;
      }

      for (i=1; i<=9; i++) Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (11 - i);
      Resto = (Soma * 10) % 11;

      if ((Resto == 10) || (Resto == 11))  Resto = 0;
      if (Resto != parseInt(strCPF.substring(9, 10)) ){
        vm.ok = false;
        return false;
      }

      Soma = 0;
      for (i = 1; i <= 10; i++) Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (12 - i);
      Resto = (Soma * 10) % 11;

      if ((Resto == 10) || (Resto == 11))  Resto = 0;
      if (Resto != parseInt(strCPF.substring(10, 11) ) ){
        vm.ok = false;
        return false;
      }

      vm.ok = true;
      return true;
    }


    /**
     * Close dialog
     */
    function closeDialog() {
      $mdDialog.hide();
    }

  }
})();
