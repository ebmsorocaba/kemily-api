(function() {
  'use strict';

  angular
    .module('app.pagamento')
    .controller('PagamentoDialogController', PagamentoDialogController);

  /** @ngInject */
  function PagamentoDialogController($filter, $mdDialog, Pagamento, Pagamentos, Associados, User, msUtils, api, $scope, $state, $q) {
    var vm = this;

    // Data
    vm.title = 'Alterar Pagamento';
    vm.pagamento = angular.copy(Pagamento);
    vm.pagamentos = Pagamentos;
    vm.associados = Associados;
    vm.user = User;
    vm.newPagamento = false;
    vm.allFields = false;
    vm.dadosLista        = loadAll();
    vm.selectedItem  = null;
    vm.searchText    = null;
    vm.querySearch   = querySearch;

    // Formas de Pagamento
    vm.listaPgtos = ["Boleto", "Dinheiro", "Cartão"];

    // TODO Ajustar o Pagamento conforme o BackEnd
    if (!vm.pagamento) {
      vm.pagamento = {
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
      vm.newPagamento = true;
      // vm.associado.tags = [];
    }
    else {
      vm.selectedItem = vm.pagamento.formaPgto.associado.cpf;
      vm.pagamento.dataPgto = new Date(vm.pagamento.dataPgto);
    }

    // Methods
    vm.addNewPagamento = addNewPagamento;
    vm.savePagamento = savePagamento;
    vm.deletePagamentoConfirm = deletePagamentoConfirm;
    vm.closeDialog = closeDialog;
    vm.toggleInArray = msUtils.toggleInArray;
    vm.exists = msUtils.exists;
    vm.buscaCpf = buscaCpf;
    vm.cadastrarPagamento = cadastrarPagamento;
    vm.sucess = sucess;
    vm.fail = fail;
    vm.querySearch = querySearch;
    vm.createFilterFor = createFilterFor;
    vm.loadAll = loadAll;
    //////////

    /**
     * Add new pagamento
     */
    function addNewPagamento(ev) {
      // Cria o novo registro no BD
      // TODO Tratar de como enviar a [formaPgto] ao BD
      //if(vm.ok == true){
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
          }
        );

      // Adiciona uma nova linha no topo da lista na tela
        vm.pagamentos.unshift(vm.pagamento);

        closeDialog();
      //}
    }

    /**
     * Save new pagamento
     */
    function savePagamento() {
      // Atualiza a linha na tela:
      for (var i = 0; i < vm.pagamentos.length; i++) {
        if (vm.pagamentos[i].id === vm.pagamento.id) {
          vm.pagamentos[i] = angular.copy(vm.pagamento);
          break;
        }
      }

      // Grava as alterações no BD:
      api.pagamento.getById.update({
        'id': vm.pagamento.id
      },vm.pagamento,
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
     * Delete Pagamento Confirm Dialog
     */
    function deletePagamentoConfirm(ev) {
      var confirm = $mdDialog.confirm()
        .title('Você tem certeza de que deseja apagar este pagamento?')
        .htmlContent('<b>' + 'Nº pagamento: ' + '</b>' + vm.pagamento.id + '</br>' + '<b>' + 'Valor: ' + '</b>' + ($filter('currency')(vm.pagamento.valorPago, 'R$ '))+ '</br>' + ' será apagado.')
        .ariaLabel('apagar pagamento')
        .targetEvent(ev)
        .ok('OK')
        .cancel('Cancelar');

      $mdDialog.show(confirm).then(function() {

        // TODO Remover também a [formaPgto] do Pagamento.

        // Remove o Pagamento do BD
        console.log('deletePagamento @ pagamentos.controller.js');
        api.pagamento.getById.delete({
            'id': vm.pagamento.id
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

        // Remove a da lista na tela a linha deste Pagamento
        vm.pagamentos.splice(vm.pagamentos.indexOf(Pagamento), 1);
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
          $state.reload();
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
      //console.log('AQUUUUUUUUI: ' + JSON.stringify(vm.selectedItem));
      if(vm.selectedItem == null){
        return false;
      }

      if (vm.selectedItem.display) {

        //transforma o item, caso seja um cpf ele tira os traços e pontos
        vm.pagamento.formaPgto.associado.cpf = vm.selectedItem.display.replace(/\-/g,"").replace(/\./g,"");

        //Testa pra ver se a pessoa digitou um nome (algo que não é numero)
        if(isNaN(vm.pagamento.formaPgto.associado.cpf)){
          var i = 0;

          //Varre o vetor procurando o nome
          for(i=0; i<vm.associados.length; i++){
            if(vm.associados[i].nome == vm.selectedItem.display){
              //Quando achar o nome atribui o cpf do associado
              vm.pagamento.formaPgto.associado.cpf = vm.associados[i].cpf;
            }
          }
        }

        //Caso já seja um cpf só é atribuido o valor
        else{
          vm.pagamento.formaPgto.associado.cpf = vm.selectedItem.display;
        }
        // Busca o Pagamento no BD para recuperar a data de pagamento e valor esperados
          api.associado.getByCpf.get({
            'cpf': vm.pagamento.formaPgto.associado.cpf
          },
          // Sucesso
          function(response) {
            console.log(response);
            vm.associado = response;

            // Define o valor esperado
            vm.pagamento.valorPago = vm.associado.valorAtual;

            // Define a data de pagamento esperada
            vm.pagamento.vencimento.setDate(vm.associado.vencAtual);
          },
          // Erro
          function(response) {
            console.error(response);
          }
        )
      }
    }

    //procura o associado na lista
    function querySearch (query) {
      //var results = query ? vm.dadosLista.filter( createFilterFor(query) ) : vm.dadosLista;
      query = query.toLowerCase();
      var results = [''];
      var flag = 0;

      for(var j = 0; j < vm.dadosLista.length; j++) {

        vm.dadosLista[j].value = vm.dadosLista[j].value.replace(/\-/g, "");
        vm.dadosLista[j].value = vm.dadosLista[j].value.replace(/\./g, "");

        if(vm.dadosLista[j].value.search(query) !== -1) {

          if(flag === 0) {
            results.fill(vm.dadosLista[j]);
            flag = 1;
          } else {
            results.push(vm.dadosLista[j]);
          }

        }

      }

      var deferred = $q.defer();
      deferred.resolve( results );
      return deferred.promise;
    }

    /**
     * Build `dadosLista` list of key/value pairs
     */

    // carrega todos os associados
    function loadAll() {

     var i = 0;
      var allStates = null;

      //Adiciona todos os nomes e cpf dos associados
      for(i=0; i<vm.associados.length; i++){
        allStates += ', ' + vm.associados[i].nome + ', ' + vm.associados[i].cpf;
        //console.log(vm.associados[i].nome);
      }


      return allStates.split(/, +/g).map(
        function (state) {
          return {

          //Cria um objeto com duas propriedades, o que deve ser exibido(display) e o valor verdadeiro (value)
            value: state.toLowerCase(),
            display: state
          };
        });
    }

    //Cria uma função de filtro
    function createFilterFor(query) {
      var lowercaseQuery = angular.lowercase(query);

      return function filterFn(state) {
        return (state.value.indexOf(lowercaseQuery) === 0);
      };
    }

    /**
     * Close dialog
     */
    function closeDialog() {
      $mdDialog.hide();
    }

  }
})();
